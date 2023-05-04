package org.lame.remembrall.commands;

import org.lame.remembrall.chatsession.ChatSession;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public abstract class BaseBotCommand extends BotCommand {
    protected ChatSession chatSession;

    public BaseBotCommand(String commandIdentifier, String description, ChatSession chatSession) {
        super(commandIdentifier, description);
        this.chatSession = chatSession;
    }

    public abstract void execute(AbsSender absSender, User user, Chat chat, String[] strings);
}
