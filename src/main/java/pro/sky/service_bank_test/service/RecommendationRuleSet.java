package pro.sky.service_bank_test.service;

import pro.sky.service_bank_test.model.Recommendation;

import java.util.Optional;
import java.util.UUID;

public interface RecommendationRuleSet {

    Optional<Recommendation> checkRule(UUID id);
}
