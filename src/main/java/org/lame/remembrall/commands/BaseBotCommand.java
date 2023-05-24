package org.lame.remembrall.commands;

import org.lame.remembrall.chatsession.ChatSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public abstract class BaseBotCommand extends BotCommand {
    @Autowired
    protected ChatSession chatSession;

    public BaseBotCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    public abstract void execute(AbsSender absSender, User user, Chat chat, String[] strings);
}
