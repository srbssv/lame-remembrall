package org.lame.remembrall;

import org.lame.remembrall.handlers.AnyMessageHandler;
import org.lame.remembrall.handlers.commands.RemindMeCommandHandler;
import org.lame.remembrall.handlers.commands.StartCommandHandler;
import org.lame.remembrall.chatsession.MemoryChatSession;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App
{
    private static MemoryChatSession chatSession;
    public static HandlerRegistry handlerRegistry;
    public static void addHandlers(HandlerRegistry handlerRegistry) {
        handlerRegistry.addCommandHandlers(
                new StartCommandHandler(chatSession, "/start"),
                new RemindMeCommandHandler(chatSession, "/remind_me")
        );
        handlerRegistry.addMessageHandlers(new AnyMessageHandler(chatSession, handlerRegistry));
    }

    public static void main( String[] args ) {
        Config config = new Config();
        handlerRegistry = new HandlerRegistry();
        chatSession = new MemoryChatSession();
        addHandlers(handlerRegistry);
        Bot bot = new Bot(config.apiKey, handlerRegistry);
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
