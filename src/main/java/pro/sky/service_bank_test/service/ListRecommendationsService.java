package pro.sky.service_bank_test.service;

import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.model.Recommendation;

import java.util.ArrayList;
import java.util.List;

import static pro.sky.service_bank_test.util.UtilVariableText.*;
import static pro.sky.service_bank_test.util.UtilVariableUUID.*;

@Service
public class ListRecommendationsService {

    private final Recommendation recommendation;

    public ListRecommendationsService(Recommendation recommendation) {
        this.recommendation = recommendation;
    }

    public List<Recommendation> getRecommendations() {
        List<Recommendation> recommendationCollection = new ArrayList<>();

        Recommendation invest = new Recommendation(INVEST_ID,
                "Invest 500", INVEST);

        Recommendation saving = new Recommendation(SAVING_ID,
                  "Top Saving", SAVING);

        Recommendation credit = new Recommendation(CREDIT_ID,
                  "Простой кредит", CREDIT);

        recommendationCollection.add(invest);
        recommendationCollection.add(saving);
        recommendationCollection.add(credit);

        return recommendationCollection;
    }

}
