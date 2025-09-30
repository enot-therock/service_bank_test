package pro.sky.service_bank_test.service;

import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.model.Recommendation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final List<RecommendationRuleSet> ruleSets;

    public RecommendationService(List<RecommendationRuleSet> ruleSets) {
        this.ruleSets = ruleSets;
    }

    public List<Recommendation> findRecommendationFromUser(UUID user) {
        return ruleSets.stream()
                .map(r -> r.checkRule(user))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
