package org.lame.remembrall;

import org.lame.remembrall.chatsession.ChatSession;
import org.ocpsoft.prettytime.nlp.PrettyTimeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Component
public class Bot extends TelegramLongPollingCommandBot {
    @Autowired
    private Config config;
    @Autowired
    private ChatSession chatSession;

    public Bot(Config config, ChatSession chatSession) {
        super();
    }

    @Override
    public String getBotToken() {
        return config.apiKey;
    }

    @Override
    public String getBotUsername() {
        return "Lame Remembrall";
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Chat chat = update.getMessage().getChat();
        Message message = update.getMessage();
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
                new Timer().schedule(new ReminderTask(this, chat, reminderText),
                        Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
                chatSession.setState(chatId, State.IDLE);
                break;
            }
        }
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
