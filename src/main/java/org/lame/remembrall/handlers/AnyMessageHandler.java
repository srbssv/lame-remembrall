package org.lame.remembrall.handlers;

import org.lame.remembrall.HandlerRegistry;
import org.lame.remembrall.ReminderTask;
import org.lame.remembrall.chatsession.IChatSession;
import org.lame.remembrall.handlers.objects.MessageHandler;
import org.lame.remembrall.State;
import org.ocpsoft.prettytime.nlp.PrettyTimeParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class AnyMessageHandler extends MessageHandler {
    public AnyMessageHandler(IChatSession stateManager, HandlerRegistry handlerRegistry) {
        super(stateManager, handlerRegistry);
    }
    @Override
    public void processMessage(AbsSender absSender, Chat chat, Message message) {
        String chatId = chat.getId().toString();
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        switch (chatSession.getState(chatId)) {
            case REMINDER_TEXT: {
                chatSession.setReminder(chatId, message.getText());
                msg.setText("Input date and time:");
                chatSession.setState(chatId, State.REMINDER_DATETIME);
                break;
            }
            case REMINDER_DATETIME: {
                List<Date> dates = new PrettyTimeParser().parse(message.getText());
                if (dates.isEmpty()) {
                    msg.setText("Incompatible format! Input again:");
                    break;
                }
                LocalDateTime dateTime = dates.get(0).toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                if (dateTime.isBefore(LocalDateTime.now())) {
                    msg.setText("Date and time must be in the future! Input again:");
                    break;
                }
                chatSession.setDateTime(chatId, dateTime);
                String reminderText = chatSession.getReminder(chatId);
                msg.setText(String.format(
                        "New reminder added!\nText: %s\nDate: %s",
                        reminderText,
                        dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
                ));
                new Timer().schedule(new ReminderTask(absSender, chat, reminderText),
                        Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
                chatSession.setState(chatId, State.IDLE);
                break;
            }
        }
        try {
            absSender.execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
