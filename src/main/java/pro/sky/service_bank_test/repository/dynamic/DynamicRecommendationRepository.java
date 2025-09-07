package pro.sky.service_bank_test.repository.dynamic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.service_bank_test.model.RecommendationByRule;

@Repository
public interface DynamicRecommendationRepository extends JpaRepository<RecommendationByRule, Long> {
}
