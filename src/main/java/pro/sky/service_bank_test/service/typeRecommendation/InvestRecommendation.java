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
public class InvestRecommendation implements RecommendationRuleSet {

    private final RecommendationsRepository recommendationsRepository;
    private final ListRecommendationsService listRecommendationsService;

    public InvestRecommendation(RecommendationsRepository recommendationsRepository,
                                ListRecommendationsService listRecommendationsService) {
        this.recommendationsRepository = recommendationsRepository;
        this.listRecommendationsService = listRecommendationsService;
    }

    @Override
    public Optional<Recommendation> checkRule(UUID uuid) {
        if (recommendationsRepository.findUsersFromInvest().equals(uuid)) {
            Stream<Recommendation> invest = listRecommendationsService.getRecommendations().stream().
                    filter(recommendation -> recommendation.getName().equals("invest"));
            return Optional.of((Recommendation) invest);
        }
        return Optional.empty();
    }
}
