package org.lame.remembrall;

public class Config {
    public String apiKey;

    public Config() {
        apiKey = System.getenv("TELEGRAM_API_KEY");
    }
}
