package pro.sky.service_bank_test.service.dynamic;

import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.model.RecommendationByRule;
import pro.sky.service_bank_test.model.Rule;
import pro.sky.service_bank_test.repository.dynamic.DynamicRecommendationRepository;
import pro.sky.service_bank_test.service.statisticService.StatisticRuleService;

import java.util.List;

@Service
public class DynamicRecommendationService {

    private final DynamicRecommendationRepository dynamicRecommendationRepository;
    private final StatisticRuleService statisticRuleService;

    public DynamicRecommendationService(DynamicRecommendationRepository dynamicRecommendationRepository,
                                        StatisticRuleService statisticRuleService) {
        this.dynamicRecommendationRepository = dynamicRecommendationRepository;
        this.statisticRuleService = statisticRuleService;
    }

    public RecommendationByRule addRecommendation(RecommendationByRule recommendationByRule) {
        for (Rule rule : recommendationByRule.getRule()) {
            rule.setRecommendationByRule(recommendationByRule);
            statisticRuleService.incrementRuleCounter(rule.getId());
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
