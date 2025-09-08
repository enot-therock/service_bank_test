package pro.sky.service_bank_test.service.dynamic;

import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.controller.DynamicRulesController;
import pro.sky.service_bank_test.model.Rule;
import pro.sky.service_bank_test.model.StatisticRule;
import pro.sky.service_bank_test.model.StatisticRuleResponse;
import pro.sky.service_bank_test.repository.dynamic.DynamicRuleRepository;
import pro.sky.service_bank_test.service.statisticService.StatisticRuleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
