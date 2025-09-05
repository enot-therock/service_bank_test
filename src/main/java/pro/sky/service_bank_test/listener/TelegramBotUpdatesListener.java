package pro.sky.service_bank_test.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.listener.command.Command;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener{

    @Autowired
    private final TelegramBot telegramBot;
    private final List<Command> commands;

    public TelegramBotUpdatesListener(TelegramBot telegramBot,
                                      List<Command> commands) {
        this.telegramBot = telegramBot;
        this.commands = commands;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {

            String message = update.message().text();

            Command command = commands.stream()
                    .filter(c -> c.getCall().equals(message))
                    .findFirst()
                    .orElse(null);

            if (command != null) {
                telegramBot.execute(command.execute(update));
            } else {
                System.out.println("Такого username - " +
                        StringUtils.substringAfterLast(command.getCall(), "_") + " нет");
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
