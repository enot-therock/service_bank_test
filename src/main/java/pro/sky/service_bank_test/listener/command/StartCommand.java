package pro.sky.service_bank_test.listener.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class StartCommand implements Command {

    @Override
    public SendMessage execute(Update update) {
        return new SendMessage(update.message().chat().id(),
                "Привет!!! " + update.message().chat().username() +
                        " этот бот поможет вам подобрать нужный продукт банка. Введите команду" +
                        " /username");
    }

    @Override
    public String getCall() {
        return "/start";
    }
}
