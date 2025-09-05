package pro.sky.service_bank_test.service.dynamic;

import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.model.RecommendationByRule;
import pro.sky.service_bank_test.model.Rule;
import pro.sky.service_bank_test.repository.DynamicRecommendationRepository;
import pro.sky.service_bank_test.repository.DynamicRuleRepository;

import java.util.List;

@Service
public class DynamicRecommendationService {

    private final DynamicRecommendationRepository dynamicRecommendationRepository;

    public DynamicRecommendationService(DynamicRecommendationRepository dynamicRecommendationRepository) {
        this.dynamicRecommendationRepository = dynamicRecommendationRepository;
    }

    public RecommendationByRule addRecommendation(RecommendationByRule recommendationByRule) {
        for (Rule rule : recommendationByRule.getRule()) {
            rule.setRecommendationByRule(recommendationByRule);
        }
        return dynamicRecommendationRepository.save(recommendationByRule);
    }

    public void deleteRecommendation(Long id) {
        dynamicRecommendationRepository.deleteById(id);
    }

    public List<RecommendationByRule> getAllRecommendation() {
        return dynamicRecommendationRepository.findAll();
    }
}
