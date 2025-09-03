package pro.sky.service_bank_test.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.listener.model.RecommendationByUser;
import pro.sky.service_bank_test.listener.service.UserRecommendationService;
import pro.sky.service_bank_test.model.Recommendation;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener{

    @Autowired
    private final TelegramBot telegramBot;
    private final UserRecommendationService userRecommendation;

    public TelegramBotUpdatesListener(TelegramBot telegramBot,
                                      UserRecommendationService userRecommendation) {
        this.telegramBot = telegramBot;
        this.userRecommendation = userRecommendation;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    @Transactional
    public int process(List<Update> updates) {
        updates.forEach(update -> {

            String text = "Новые продуукты для Вас: ";

            String message = update.message().text();

            String answerByStart = "Привет!!! " + update.message().chat().username() +
                    " этот бот поможет вам подобрать нужный продукт банка. Введите команду" +
                    " /recommend username(ваш username)";

            if (message.equals("/start")) {
                telegramBot.execute(new SendMessage(update.message().chat().id(), answerByStart));
            }
            if (message.equals("/recommend username")) {
                RecommendationByUser user = userRecommendation.byUser(update.message().text());
                telegramBot.execute(new SendMessage(update.message().chat().id(),
                        "Hello " + user.getFirsName() + " " + user.getLastName() + " " + text));

                List<Recommendation> recommendations = userRecommendation.recommendations(user);

                telegramBot.execute(new SendMessage(user.getId(),
                        recommendations.get(0).getName() + recommendations.get(0).getText()));
            } else {
                System.out.println("Пользователь не найден");
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public void sendMessage(Long id, String message) {
        SendMessage sendMessage = new SendMessage(id, message);
        telegramBot.execute(sendMessage);
    }
}
