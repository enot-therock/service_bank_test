package pro.sky.service_bank_test.service;

import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.model.Recommendation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecommendationService {

    private final List<RecommendationRuleSet> ruleSets;

    public RecommendationService(List<RecommendationRuleSet> ruleSets) {
        this.ruleSets = ruleSets;
    }

    public Optional<Recommendation> findRecommendationFromUser(UUID user) {
        return ruleSets.stream()
                .map(rule -> rule.checkRule(user))
                .flatMap(Optional::stream)
                .findFirst();
    }
}
