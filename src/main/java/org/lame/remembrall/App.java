package org.lame.remembrall;

import org.lame.remembrall.chatsession.MemoryChatSession;
import org.lame.remembrall.commands.RemindMeBotCommand;
import org.lame.remembrall.commands.StartBotCommand;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
    public static void main(String[] args) {
        Config config = new Config();
        MemoryChatSession chatSession = new MemoryChatSession();
        Bot bot = new Bot(config, chatSession);
        bot.registerAll(
                new StartBotCommand("start", "", chatSession),
                new RemindMeBotCommand("remind_me", "", chatSession)
        );
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
