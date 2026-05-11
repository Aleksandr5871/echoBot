import bot.EchoBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.config.echobot.Config;
import ru.config.echobot.ConfigReader;
import ru.config.echobot.ConfigReaderEnvironment;




public class App {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(App.class);
        ConfigReader configReader = new ConfigReaderEnvironment();
        Config config = configReader.read();

        TelegramClient telegramClient = new OkHttpTelegramClient(config.botApiToken());

        try(TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            botsApplication.registerBot(config.botApiToken(), new EchoBot(telegramClient));
            log.info("Bot is start");
            Thread.currentThread().join();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
}
