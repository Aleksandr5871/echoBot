package bot;

import bot.command.CommandDispatcher;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class EchoBot implements LongPollingSingleThreadUpdateConsumer {
    private final TelegramClient telegramClient;
    private final CommandDispatcher commandDispatcher;

    public EchoBot(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
        this.commandDispatcher = new CommandDispatcher(telegramClient);
    }

    @Override
    public void consume(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            System.out.println("В сообщении нет текста!");
            return;
        }

        String text = update.getMessage().getText().stripLeading();
        if (text.startsWith("/")){
            commandDispatcher.dispatch(text,update);
        }

        Long chatId = update.getMessage().getChatId();


        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();

        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
           e.printStackTrace();
        }

        System.out.println(update.getMessage().getText());
    }

}
