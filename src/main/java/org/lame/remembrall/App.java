package org.lame.remembrall;

import org.lame.remembrall.commands.RemindMeBotCommand;
import org.lame.remembrall.commands.StartBotCommand;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigAppContext.class);
        Bot bot = ctx.getBean(Bot.class);
        bot.registerAll(
                ctx.getBean(StartBotCommand.class),
                ctx.getBean(RemindMeBotCommand.class)
        );
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
