package org.lame.remembrall.chatsession;

import org.lame.remembrall.State;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.HashMap;

@Component
public final class MemoryChatSession implements ChatSession {
    private HashMap<String, State> chatStates;
    private HashMap<String, String> chatReminders;
    private HashMap<String, LocalDateTime> chatDateTime;

    public MemoryChatSession() {
        chatStates = new HashMap<>();
        chatReminders = new HashMap<>();
        chatDateTime = new HashMap<>();
    }

    public boolean exists(String chatId) {
        return chatStates.containsKey(chatId);
    }

    public void add(String chatId) {
        chatStates.put(chatId, State.IDLE);
    }

    public State getState(String chatId) {
        return chatStates.get(chatId);
    }

    public void setState(String chatId, State state) {
        chatStates.put(chatId, state);
    }

    public void setReminder(String chatId, String reminder) {
        chatReminders.put(chatId, reminder);
    }

    public String getReminder(String chatId) {
        return chatReminders.get(chatId);
    }

    public void setDateTime(String chatId, LocalDateTime dateTime) {
        chatDateTime.put(chatId, dateTime);
    }

    public LocalDateTime getDateTime(String chatId) {
        return chatDateTime.get(chatId);
    }
}
