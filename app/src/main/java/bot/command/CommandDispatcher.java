package bot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.HashMap;
import java.util.Map;

public class CommandDispatcher {

    Logger log = LoggerFactory.getLogger(UserIdCommand.class);
    private final Map<String, Command> commandMap = new HashMap<>();

    public CommandDispatcher(TelegramClient telegramClient) {
        commandMap.put("/start", new StartCommand(telegramClient));
        commandMap.put("/myid", new UserIdCommand(telegramClient));
    }

    public void dispatch(String commandText, Update update){
        Command command = commandMap.get(commandText.split("\\s")[0].toLowerCase());
        log.info("Обработка команды %s%n",commandText);
        if (command!=null){command.execute(update);}
        else {
            log.error("Команда %s не найдена%n",commandText);
        }
    }
}
