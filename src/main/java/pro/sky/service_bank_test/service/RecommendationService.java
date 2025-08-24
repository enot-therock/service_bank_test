package pro.sky.service_bank_test.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.model.Recommendation;
import pro.sky.service_bank_test.repository.RecommendationsRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RecommendationService {

    private final JdbcTemplate jdbcTemplate;
    private final RecommendationsRepository recommendationsRepository;

    public RecommendationService(JdbcTemplate jdbcTemplate, RecommendationsRepository recommendationsRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.recommendationsRepository = recommendationsRepository;
    }

    public List<Recommendation> findRecommendationFromUser(UUID user) {
        return null;
    }
}
