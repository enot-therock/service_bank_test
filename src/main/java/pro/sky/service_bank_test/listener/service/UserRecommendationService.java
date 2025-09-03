package pro.sky.service_bank_test.listener.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.listener.model.RecommendationByUser;
import pro.sky.service_bank_test.model.Recommendation;
import pro.sky.service_bank_test.service.RecommendationService;

import java.util.List;

import static pro.sky.service_bank_test.util.UtilVariableSQL.NAME_SQL;

@Service
public class UserRecommendationService {

    private final RecommendationService recommendationService;
    private final JdbcTemplate jdbcTemplate;

    public UserRecommendationService(RecommendationService recommendationService,
                                     JdbcTemplate jdbcTemplate) {
        this.recommendationService = recommendationService;
        this.jdbcTemplate = jdbcTemplate;
    }

    public RecommendationByUser byUser(String userName) {
        return jdbcTemplate.queryForObject(NAME_SQL, RecommendationByUser.class);
    }

    public List<Recommendation> recommendations(RecommendationByUser user) {
        return recommendationService.findRecommendationFromUser(user.getUser());
    }
}
