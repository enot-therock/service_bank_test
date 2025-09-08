package pro.sky.service_bank_test.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.service_bank_test.model.Rule;
import pro.sky.service_bank_test.model.StatisticRule;
import pro.sky.service_bank_test.service.dynamic.DynamicRuleService;
import pro.sky.service_bank_test.service.statisticService.StatisticRuleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dynamicRule")
public class DynamicRulesController {

    private final DynamicRuleService dynamicRuleService;
    private final StatisticRuleService statisticRuleService;

    public DynamicRulesController(DynamicRuleService dynamicRuleService,
                                  StatisticRuleService statisticRuleService) {
        this.dynamicRuleService = dynamicRuleService;
        this.statisticRuleService = statisticRuleService;
    }

    @PostMapping
    public Rule createRule(@RequestBody Rule rule) {
        return dynamicRuleService.addRule(rule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Rule> deleteRule(@PathVariable long id) {
        dynamicRuleService.deleteRule(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Rule>> getAllRules() {
        return ResponseEntity.ok(dynamicRuleService.getAllRules());
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<StatisticRuleResponse>> getRulesStatistics() {
        List<StatisticRule> statistics = statisticRuleService.getAllStatistics();

        List<Rule> allRules = dynamicRuleService.getAllRules();

        List<StatisticRuleResponse> response = allRules.stream()
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

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public void removeRule(@PathVariable Long ruleId) {
        statisticRuleService.deleteRuleStatistics(ruleId);
    }

    public record StatisticRuleResponse(
            Long ruleId,
            Integer triggerCount
    ){}
}
