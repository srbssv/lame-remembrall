package org.lame.remembrall;

import org.lame.remembrall.chatsession.MemoryChatSession;
import org.lame.remembrall.commands.RemindMeBotCommand;
import org.lame.remembrall.commands.StartBotCommand;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
    private static MemoryChatSession chatSession;
    private static Bot bot;
    private static Config config;

    private static void createBot() {
        bot = new Bot(config, chatSession);
    }

    private static void createCommands() {
        bot.registerAll(
                new StartBotCommand("start", "", chatSession),
                new RemindMeBotCommand("remind_me", "", chatSession)
        );
    }

    public static void main(String[] args) {
        config = new Config();
        chatSession = new MemoryChatSession();
        createBot();
        createCommands();
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
