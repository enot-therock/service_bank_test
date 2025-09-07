package pro.sky.service_bank_test.service.typeRecommendation;

import org.springframework.stereotype.Component;
import pro.sky.service_bank_test.model.Recommendation;
import pro.sky.service_bank_test.repository.RecommendationsRepository;
import pro.sky.service_bank_test.service.ListRecommendationsService;
import pro.sky.service_bank_test.service.RecommendationRuleSet;

import java.util.Optional;
import java.util.UUID;

import static pro.sky.service_bank_test.util.UtilVariableUUID.INVEST_ID;

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
        if (recommendationsRepository.findUsersFromInvest().contains(uuid)) {
            return listRecommendationsService.getRecommendations().stream()
                    .filter(recommendation -> INVEST_ID.equals(recommendation.getId()))
                    .findFirst();
        }
        return Optional.empty();
    }
}
