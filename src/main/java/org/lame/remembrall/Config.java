package org.lame.remembrall;

import org.springframework.stereotype.Component;

@Component
public class Config {
    public String apiKey;
    public Config() {
        apiKey = System.getenv("TELEGRAM_API_KEY");
    }
}
