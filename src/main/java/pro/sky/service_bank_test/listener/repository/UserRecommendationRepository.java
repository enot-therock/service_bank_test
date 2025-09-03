package pro.sky.service_bank_test.listener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.service_bank_test.listener.model.RecommendationByUser;

@Repository
public interface UserRecommendationRepository extends JpaRepository<RecommendationByUser, Long> {
}
