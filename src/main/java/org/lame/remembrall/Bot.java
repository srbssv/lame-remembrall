package org.lame.remembrall;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    private HandlerRegistry handlerRegistry;
    public Bot(String botToken, HandlerRegistry handlerRegistry) {
        super(botToken);
        this.handlerRegistry = handlerRegistry;
    }
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Chat chat = message.getChat();

        handlerRegistry.getCommandHandlers()
                .stream()
                .forEach( handler -> handler.handle(this, chat, message) );
        handlerRegistry.getMessageHandlers()
                .stream()
                .forEach( handler -> handler.handle(this, chat, message) );
    }

    @Override
    public String getBotUsername() {
        return "Lame Remembrall";
    }
}
