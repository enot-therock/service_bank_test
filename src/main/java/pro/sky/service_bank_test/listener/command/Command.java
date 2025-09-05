package pro.sky.service_bank_test.listener.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface Command {

    SendMessage execute(Update update);

    String getCall();
}
