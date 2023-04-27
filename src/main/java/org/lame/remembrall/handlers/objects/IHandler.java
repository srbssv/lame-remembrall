package org.lame.remembrall.handlers.objects;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface IHandler {
    void handle(AbsSender absSender, Chat chat, Message message);
}
