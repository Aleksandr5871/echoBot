package bot;

import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

public class EchoBot implements LongPollingSingleThreadUpdateConsumer {

    @Override
    public void consume(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()){
            System.out.println("В сообщении нет текста!");
            return;
        }
        System.out.println(update.getMessage().getText());
    }

}
