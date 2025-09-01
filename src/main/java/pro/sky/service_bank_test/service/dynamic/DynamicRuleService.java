package pro.sky.service_bank_test.service.dynamic;

import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.model.RecommendationByRule;
import pro.sky.service_bank_test.model.Rule;
import pro.sky.service_bank_test.repository.DynamicRuleRepository;

import java.util.List;

@Service
public class DynamicRuleService {

    private final DynamicRuleRepository dynamicRuleRepository;

    public DynamicRuleService(DynamicRuleRepository dynamicRuleRepository) {
        this.dynamicRuleRepository = dynamicRuleRepository;
    }

    public Rule addRule(Rule rule) {
        return dynamicRuleRepository.save(rule);
    }

    public void deleteRule(Long id) {
        dynamicRuleRepository.deleteById(id);
    }

    public List<Rule> getAllRules() {
        return dynamicRuleRepository.findAll();
    }
}
