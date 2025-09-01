package pro.sky.service_bank_test.service;

import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.model.Recommendation;

import java.util.ArrayList;
import java.util.List;

import static pro.sky.service_bank_test.util.UtilVariableText.*;

@Service
public class ListRecommendationsService {

    private final Recommendation recommendation;

    public ListRecommendationsService(Recommendation recommendation) {
        this.recommendation = recommendation;
    }

    public List<Recommendation> getRecommendations() {
        List<Recommendation> recommendationCollection = new ArrayList<>();

        Recommendation invest = new Recommendation("147f6a0f-3b91-413b-ab99-87f081d60d5a",
                "Invest 500",INVEST);

        Recommendation saving = new Recommendation("59efc529-2fff-41af-baff-90ccd7402925",
                  "Top Saving", SAVING);

        Recommendation credit = new Recommendation("ab138afb-f3ba-4a93-b74f-0fcee86d447f",
                  "Простой кредит", CREDIT);

        recommendationCollection.add(invest);
        recommendationCollection.add(saving);
        recommendationCollection.add(credit);

        return recommendationCollection;
    }

}
