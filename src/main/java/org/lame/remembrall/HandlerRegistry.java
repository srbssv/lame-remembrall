package org.lame.remembrall;

import org.lame.remembrall.handlers.objects.CommandHandler;
import org.lame.remembrall.handlers.objects.MessageHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HandlerRegistry {
    private final List<CommandHandler> commandHandlers;
    private final List<MessageHandler> messageHandlers;
    public HandlerRegistry() {
        commandHandlers = new ArrayList<>();
        messageHandlers = new ArrayList<>();
    }
    public void addCommandHandlers(CommandHandler... handlers) {
        commandHandlers.addAll(Arrays.asList(handlers));
    }
    public void addMessageHandlers(MessageHandler... handlers) {
        Collections.addAll(messageHandlers, handlers);
    }
    public List<CommandHandler> getCommandHandlers() {
        return commandHandlers;
    }
    public List<MessageHandler> getMessageHandlers() {
        return messageHandlers;
    }
}
