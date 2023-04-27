package org.lame.remembrall.handlers.commands;

import org.lame.remembrall.State;
import org.lame.remembrall.chatsession.IChatSession;
import org.lame.remembrall.handlers.objects.CommandHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RemindMeCommandHandler extends CommandHandler {
    public RemindMeCommandHandler(IChatSession chatSession, String commandIdentifier) {
        super(chatSession, commandIdentifier);
    }

    @Override
    public void processCommand(AbsSender absSender, Chat chat, Message message) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chat.getId());
        msg.setText("Reminder text:");
        try {
            absSender.execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        chatSession.setState(chat.getId().toString(), State.REMINDER_TEXT);
    }
}
