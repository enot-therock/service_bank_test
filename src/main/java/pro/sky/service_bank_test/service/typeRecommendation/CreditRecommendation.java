package pro.sky.service_bank_test.service.typeRecommendation;

import org.springframework.stereotype.Component;
import pro.sky.service_bank_test.model.Recommendation;
import pro.sky.service_bank_test.repository.RecommendationsRepository;
import pro.sky.service_bank_test.service.ListRecommendationsService;
import pro.sky.service_bank_test.service.RecommendationRuleSet;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class CreditRecommendation implements RecommendationRuleSet {

    private final RecommendationsRepository recommendationsRepository;
    private final ListRecommendationsService listRecommendationsService;

    public CreditRecommendation(RecommendationsRepository recommendationsRepository,
                                ListRecommendationsService listRecommendationsService) {
        this.recommendationsRepository = recommendationsRepository;
        this.listRecommendationsService = listRecommendationsService;
    }

    @Override
    public Optional<Recommendation> checkRule(UUID uuid) {
        if (recommendationsRepository.findUsersFromCredit().equals(uuid)) {
            Stream<Recommendation> credit = listRecommendationsService.getRecommendations().stream().
                    filter(recommendation -> recommendation.getName().equals("credit"));
            return Optional.of((Recommendation) credit);
        }
        return Optional.empty();
    }
}
