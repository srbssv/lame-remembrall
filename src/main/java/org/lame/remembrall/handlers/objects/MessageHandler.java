package org.lame.remembrall.handlers.objects;

import org.lame.remembrall.HandlerRegistry;
import org.lame.remembrall.chatsession.IChatSession;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;

public abstract class MessageHandler extends BaseHandler {
    private List<String> registeredCommandIdentifiers;
    public MessageHandler(IChatSession chatSession, HandlerRegistry handlerRegistry) {
        super(chatSession);
        registeredCommandIdentifiers = handlerRegistry.getCommandHandlers()
                .stream()
                .map( handler -> handler.commandIdentifier).toList();
    }
    @Override
    public void handle(AbsSender absSender, Chat chat, Message message) {
        if (message.hasText())
            if (!registeredCommandIdentifiers.contains(message.getText()))
                processMessage(absSender, chat, message);
    }
    public abstract void processMessage(AbsSender absSender, Chat chat, Message message);
}
