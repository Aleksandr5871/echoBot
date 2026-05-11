import bot.EchoBot;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;


public class App {
    public static void main(String[] args) {
        String tokenBot = "";


        try(TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            botsApplication.registerBot(tokenBot, new EchoBot());
            Thread.currentThread().join();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
}
