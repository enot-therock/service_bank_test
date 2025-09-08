package pro.sky.service_bank_test.repository.statistic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.service_bank_test.model.StatisticRule;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticRuleRepository extends JpaRepository<StatisticRule,  Long> {
    Optional<StatisticRule> findByRuleId(Long ruleId);

    List<StatisticRule> findAllByOrderByTriggerCountDesc();

    void deleteByRuleId(Long ruleId);
}
