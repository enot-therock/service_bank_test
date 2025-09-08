package pro.sky.service_bank_test.model;

public class StatisticRuleResponse {

    private Long ruleId;
    private Integer triggerCount;

    public StatisticRuleResponse(Long ruleId, Integer triggerCount) {
        this.ruleId = ruleId;
        this.triggerCount = triggerCount;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getTriggerCount() {
        return triggerCount;
    }

    public void setTriggerCount(Integer triggerCount) {
        this.triggerCount = triggerCount;
    }
}
