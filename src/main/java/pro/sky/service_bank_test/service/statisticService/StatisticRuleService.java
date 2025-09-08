package pro.sky.service_bank_test.service.statisticService;

import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.model.StatisticRule;
import pro.sky.service_bank_test.repository.statistic.StatisticRuleRepository;
import pro.sky.service_bank_test.service.dynamic.DynamicRuleService;

import java.util.List;

@Service
public class StatisticRuleService {

    private final StatisticRuleRepository statisticRuleRepository;
    private final DynamicRuleService dynamicRuleService;

    public StatisticRuleService(StatisticRuleRepository statisticRuleRepository,
                                DynamicRuleService dynamicRuleService) {
        this.statisticRuleRepository = statisticRuleRepository;
        this.dynamicRuleService = dynamicRuleService;
    }

    public void incrementRuleCounter(Long ruleId) {
        StatisticRule statistic = statisticRuleRepository.findByRuleId(ruleId)
                .orElseGet(() -> createNewStatistic(ruleId));

        statistic.incrementCount();
        statisticRuleRepository.save(statistic);
    }

    private StatisticRule createNewStatistic(Long ruleId) {
        StatisticRule statistic = new StatisticRule(ruleId);
        return statisticRuleRepository.save(statistic);
    }

    public List<StatisticRule> getAllStatistics() {
        return statisticRuleRepository.findAllByOrderByTriggerCountDesc();
    }

    public void deleteRuleStatistics(Long ruleId) {
        statisticRuleRepository.deleteByRuleId(ruleId);
    }

    public StatisticRule getStatisticByRuleId(Long ruleId) {
        return statisticRuleRepository.findByRuleId(ruleId)
                .orElseThrow(() -> new RuntimeException("Statistic not found for rule: " + ruleId));
    }

    public void removeRule(Long ruleId) {
        dynamicRuleService.deleteRule(ruleId);
        deleteRuleStatistics(ruleId);
    }
}
