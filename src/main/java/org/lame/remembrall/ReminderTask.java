package org.lame.remembrall;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.TimerTask;

public class ReminderTask extends TimerTask {
    private AbsSender absSender;
    private Chat chat;
    private String messageText;
    public ReminderTask(AbsSender absSender, Chat chat, String messageText) {
        this.absSender = absSender;
        this.chat = chat;
        this.messageText = messageText;
    }
    @Override
    public void run() {
        SendMessage msg = new SendMessage();
        msg.setChatId(chat.getId());
        msg.setText(messageText);
        try {
            absSender.execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
