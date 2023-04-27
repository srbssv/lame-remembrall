package org.lame.remembrall.handlers.commands;

import org.lame.remembrall.State;
import org.lame.remembrall.chatsession.IChatSession;
import org.lame.remembrall.handlers.objects.CommandHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommandHandler extends CommandHandler {
    public StartCommandHandler(IChatSession chatSession, String commandIdentifier) {
        super(chatSession, commandIdentifier);
    }

    @Override
    public void processCommand(AbsSender absSender, Chat chat, Message message) {
        SendMessage msg = new SendMessage();
        String chatId = chat.getId().toString();
        msg.setChatId(chatId);
        msg.setText("Hi!\n/remind_me");
        try {
            absSender.execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        if (!chatSession.exists(chatId))
            chatSession.add(chatId);

        chatSession.setState(chatId, State.IDLE);
    }
}
