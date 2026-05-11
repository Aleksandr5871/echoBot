package ru.config.echobot;

public record Config(
        String botApiToken
) {
    public Config {
        if (botApiToken == null || botApiToken.isEmpty()){
            throw new IllegalArgumentException("Токер не задан!");
        }
    }
}
