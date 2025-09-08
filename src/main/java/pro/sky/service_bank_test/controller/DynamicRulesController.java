package pro.sky.service_bank_test.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.service_bank_test.model.Rule;
import pro.sky.service_bank_test.model.StatisticRuleResponse;
import pro.sky.service_bank_test.service.dynamic.DynamicRuleService;
import pro.sky.service_bank_test.service.statisticService.StatisticResponseService;
import pro.sky.service_bank_test.service.statisticService.StatisticRuleService;

import java.util.List;

@RestController
@RequestMapping("/dynamicRule")
public class DynamicRulesController {

    private final DynamicRuleService dynamicRuleService;
    private final StatisticRuleService statisticRuleService;
    private final StatisticResponseService statisticResponseService;

    public DynamicRulesController(DynamicRuleService dynamicRuleService,
                                  StatisticRuleService statisticRuleService,
                                  StatisticResponseService statisticResponseService) {
        this.dynamicRuleService = dynamicRuleService;
        this.statisticRuleService = statisticRuleService;
        this.statisticResponseService = statisticResponseService;
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
        return ResponseEntity.ok(statisticResponseService.responses());
    }

    @DeleteMapping
    public void removeRule(@PathVariable Long ruleId) {
        statisticRuleService.deleteRuleStatistics(ruleId);
    }
}
