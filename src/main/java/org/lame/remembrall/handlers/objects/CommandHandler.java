package org.lame.remembrall.handlers.objects;

import org.lame.remembrall.chatsession.IChatSession;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

public abstract class CommandHandler extends BaseHandler {
    protected String commandIdentifier;
    public CommandHandler(IChatSession stateManager, String commandIdentifier) {
        super(stateManager);
        this.commandIdentifier = commandIdentifier;
    }

    @Override
    public void handle(AbsSender absSender, Chat chat, Message message) {
        if (!message.hasText())
            return ;
        if (message.getText().equals(commandIdentifier)) {
            processCommand(absSender, chat, message);
        }
    };
    public abstract void processCommand(AbsSender absSender, Chat chat, Message message);
}
