package pro.sky.service_bank_test.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pro.sky.service_bank_test.model.RecommendationByRule;
import pro.sky.service_bank_test.model.Rule;
import pro.sky.service_bank_test.model.StatisticRuleResponse;
import pro.sky.service_bank_test.service.dynamic.DynamicRuleService;
import pro.sky.service_bank_test.service.statisticService.StatisticResponseService;
import pro.sky.service_bank_test.service.statisticService.StatisticRuleService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DynamicRuleControllerTest {

    @Mock
    private DynamicRuleService dynamicRuleService;

    @Mock
    private StatisticRuleService statisticRuleService;

    @Mock
    private StatisticResponseService statisticResponseService;

    @InjectMocks
    private DynamicRulesController dynamicRulesController;

    @Test
    void createRule_ReturnRules() {

        RecommendationByRule recommendation = new RecommendationByRule(UUID.randomUUID(),
                "Test Product", "Product description", List.of());

        Rule inputRule = new Rule("test_query", List.of("arg1", "arg2"), false, recommendation);
        Rule expectedRule = new Rule("test_query", List.of("arg1", "arg2"), false, recommendation);

        when(dynamicRuleService.addRule(inputRule)).thenReturn(expectedRule);

        Rule result = dynamicRulesController.createRule(inputRule);

        assertThat(result).isEqualTo(expectedRule);
        verify(dynamicRuleService).addRule(inputRule);
    }

    @Test
    void deleteRule_returnOkResponse() {

        long ruleId = 1L;
        doNothing().when(dynamicRuleService).deleteRule(ruleId);

        ResponseEntity<Rule> response = dynamicRulesController.deleteRule(ruleId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(dynamicRuleService).deleteRule(ruleId);
    }

    @Test
    void getAllRules_returnListOfRules() {

        RecommendationByRule recommendation1 = new RecommendationByRule(UUID.randomUUID(),
                "Product 1", "Description 1", List.of());

        RecommendationByRule recommendation2 = new RecommendationByRule(UUID.randomUUID(),
                "Product 2", "Description 2", List.of());

        List<Rule> expectedRules = Arrays.asList(
                new Rule("query_1", List.of("arg1"), true, recommendation1),
                new Rule("query_2", List.of("arg1", "arg2"), false, recommendation2));

        when(dynamicRuleService.getAllRules()).thenReturn(expectedRules);

        ResponseEntity<List<Rule>> response = dynamicRulesController.getAllRules();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedRules);
        verify(dynamicRuleService).getAllRules();
    }

    @Test
    void getRulesStatistics_returnStatistics() {

        List<StatisticRuleResponse> expectedStatistics = Arrays.asList(
                new StatisticRuleResponse(1L, 10),
                new StatisticRuleResponse(2L, 5));

        when(statisticResponseService.responses()).thenReturn(expectedStatistics);

        ResponseEntity<List<StatisticRuleResponse>> response =
                dynamicRulesController.getRulesStatistics();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedStatistics);
        assertThat(response.getBody()).hasSize(2);
        verify(statisticResponseService).responses();
    }

    @Test
    void removeRule_deleteStatistics() {

        Long ruleId = 1L;
        doNothing().when(statisticRuleService).deleteRuleStatistics(ruleId);

        dynamicRulesController.removeRule(ruleId);

        verify(statisticRuleService).deleteRuleStatistics(ruleId);
    }
}
