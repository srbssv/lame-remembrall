package org.lame.remembrall.commands;

import org.lame.remembrall.State;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class RemindMeBotCommand extends BaseBotCommand {
    public RemindMeBotCommand() {
        super("remind_me", "");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
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
