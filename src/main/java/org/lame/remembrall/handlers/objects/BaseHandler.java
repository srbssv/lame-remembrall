package org.lame.remembrall.handlers.objects;

import org.lame.remembrall.chatsession.IChatSession;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

public abstract class BaseHandler implements IHandler {
    protected IChatSession chatSession;
    public BaseHandler(IChatSession chatSession) {
        this.chatSession = chatSession;
    }
    public abstract void handle(AbsSender absSender, Chat chat, Message message);
}
