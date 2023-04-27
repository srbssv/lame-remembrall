package org.lame.remembrall.chatsession;

import org.lame.remembrall.State;

import java.time.LocalDateTime;

public interface IChatSession {
    boolean exists(String chatId);

    void add(String chatId);

    State getState(String chatId);

    void setState(String chatId, State state);

    void setReminder(String chatId, String reminder);

    String getReminder(String chatId);

    void setDateTime(String chatId, LocalDateTime dateTime);

    LocalDateTime getDateTime(String chatId);
}
