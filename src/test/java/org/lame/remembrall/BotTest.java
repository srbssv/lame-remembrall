package org.lame.remembrall;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lame.remembrall.handlers.objects.CommandHandler;
import org.lame.remembrall.handlers.objects.MessageHandler;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BotTest {
    @Mock
    private CommandHandler commandHandler;
    @Mock
    private MessageHandler messageHandler;
    @Mock
    private Update update;
    @Mock
    private Chat chat;
    @Mock
    private Message message;
    private Bot bot;
    private HandlerRegistry handlerRegistry;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        handlerRegistry = new HandlerRegistry();
        handlerRegistry.addCommandHandlers(commandHandler);
        handlerRegistry.addMessageHandlers(messageHandler);
        bot = new Bot("token", handlerRegistry);
        when(update.getMessage()).thenReturn(message);
        when(message.getChat()).thenReturn(chat);
    }
    @Test
    public void botUpdateReceived() {
        bot.onUpdateReceived(update);
        verify(commandHandler).handle(eq(bot), eq(chat), eq(message));
        verify(messageHandler).handle(eq(bot), eq(chat), eq(message));
    }
}
