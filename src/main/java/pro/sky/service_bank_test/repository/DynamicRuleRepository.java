package pro.sky.service_bank_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.service_bank_test.model.RecommendationByRule;
import pro.sky.service_bank_test.model.Rule;

@Repository
public interface DynamicRuleRepository extends JpaRepository<Rule, Long> {
}
