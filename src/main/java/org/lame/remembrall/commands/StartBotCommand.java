package org.lame.remembrall.commands;

import org.lame.remembrall.State;
import org.lame.remembrall.chatsession.ChatSession;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartBotCommand extends BaseBotCommand {
    public StartBotCommand(String commandIdentifier, String description, ChatSession chatSession) {
        super(commandIdentifier, description, chatSession);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
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
