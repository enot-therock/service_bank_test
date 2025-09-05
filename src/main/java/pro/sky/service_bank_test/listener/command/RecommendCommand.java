package pro.sky.service_bank_test.listener.command;

import ch.qos.logback.core.util.StringUtil;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pro.sky.service_bank_test.model.RecommendationByUserTelegram;
import pro.sky.service_bank_test.listener.service.UserRecommendationService;
import pro.sky.service_bank_test.model.Recommendation;

import java.util.List;

import static pro.sky.service_bank_test.util.UtilVariableText.TEXT;

@Component
public class RecommendCommand implements Command {

    private final UserRecommendationService userRecommendationService;

    public RecommendCommand(UserRecommendationService userRecommendationService) {
        this.userRecommendationService = userRecommendationService;
    }

    @Override
    public SendMessage execute(Update update) {
        String userName = StringUtils.substringAfterLast(getCall(), "_");

        RecommendationByUserTelegram user = userRecommendationService.byUser(userName);
        List<Recommendation> recommendations = userRecommendationService.recommendations(user);

        return new SendMessage(update.message().chat().id(),
                "Hello " + user.getFirsName() + " " + user.getLastName() + " " + TEXT +
                        recommendations.get(0).getName() + recommendations.get(0).getText());
    }

    @Override
    public String getCall() {
        return "/recommend_username";
    }
}
