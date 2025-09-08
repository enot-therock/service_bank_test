package pro.sky.service_bank_test.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.listener.command.Command;
import pro.sky.service_bank_test.listener.service.UserRecommendationService;
import pro.sky.service_bank_test.model.Recommendation;
import pro.sky.service_bank_test.model.RecommendationByUserTelegram;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pro.sky.service_bank_test.util.UtilVariablePattern.PATTERN;
import static pro.sky.service_bank_test.util.UtilVariableText.TEXT;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener{

    @Autowired
    private final TelegramBot telegramBot;
    private final List<Command> commands;
    private final UserRecommendationService userRecommendationService;

    public TelegramBotUpdatesListener(TelegramBot telegramBot,
                                      List<Command> commands,
                                      UserRecommendationService userRecommendationService) {
        this.telegramBot = telegramBot;
        this.commands = commands;
        this.userRecommendationService = userRecommendationService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {

            String message = update.message().text();

            Pattern pattern = Pattern.compile(PATTERN);
            Matcher matcher = pattern.matcher(message);

            Command command = commands.stream()
                    .filter(c -> c.getCall().equals(message))
                    .findFirst()
                    .orElse(null);

            if (command != null) {
                telegramBot.execute(command.execute(update));
            }
            if (message.startsWith("/") && matcher.matches()) {

                String userName = message.substring(1);

                RecommendationByUserTelegram user = userRecommendationService.byUser(userName);
                if (user != null && user.getUserId() != null) {
                    Optional<Recommendation> recommendations = userRecommendationService.recommendations(user);


                    if (recommendations.isPresent()) {
                        Recommendation recommendation = recommendations.get();
                        String response = "Hello - " + user.getFirsName() + " " + user.getLastName() + "\n" +
                                TEXT + "\n" + recommendations.get().getName() + ": " + "\n" +
                                recommendations.get().getText();

                        telegramBot.execute(new SendMessage(update.message().chat().id(), response));
                    } else {
                        telegramBot.execute(new SendMessage(update.message().chat().id(),
                                "No recommendations for user: " + userName));
                    }
                } else {
                    telegramBot.execute(new SendMessage(update.message().chat().id(),
                            "User not found: " + userName));
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
