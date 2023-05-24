package org.lame.remembrall.commands;

import org.lame.remembrall.State;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class StartBotCommand extends BaseBotCommand {
    public StartBotCommand() {
        super("start", "");
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
