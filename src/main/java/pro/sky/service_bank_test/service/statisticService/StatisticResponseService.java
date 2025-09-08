package pro.sky.service_bank_test.service.statisticService;

import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.model.Rule;
import pro.sky.service_bank_test.model.StatisticRule;
import pro.sky.service_bank_test.model.StatisticRuleResponse;
import pro.sky.service_bank_test.service.dynamic.DynamicRuleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatisticResponseService {

    private final DynamicRuleService dynamicRuleService;
    private final StatisticRuleService statisticRuleService;

    public StatisticResponseService(DynamicRuleService dynamicRuleService,
                                    StatisticRuleService statisticRuleService) {
        this.dynamicRuleService = dynamicRuleService;
        this.statisticRuleService = statisticRuleService;
    }

    public List<StatisticRuleResponse> responses() {
        List<StatisticRule> statistics = statisticRuleService.getAllStatistics();
        List<Rule> allRules = dynamicRuleService.getAllRules();

        return allRules.stream()
                .map(rule -> {
                    Optional<StatisticRule> ruleStatistic = statistics.stream()
                            .filter(stat -> stat.getRuleId().equals(rule.getId()))
                            .findFirst();

                    return new StatisticRuleResponse(
                            rule.getId(),
                            ruleStatistic.map(StatisticRule::getTriggerCount).orElse(0)
                    );
                })
                .collect(Collectors.toList());
    }
}
