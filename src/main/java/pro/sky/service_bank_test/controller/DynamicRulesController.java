package pro.sky.service_bank_test.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.service_bank_test.model.Rule;
import pro.sky.service_bank_test.service.dynamic.DynamicRuleService;

import java.util.List;

@RestController
@RequestMapping("/dynamicRule")
public class DynamicRulesController {

    private final DynamicRuleService dynamicRuleService;

    public DynamicRulesController(DynamicRuleService dynamicRuleService) {
        this.dynamicRuleService = dynamicRuleService;
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
}
