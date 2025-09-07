package pro.sky.service_bank_test.listener.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.model.RecommendationByUserTelegram;
import pro.sky.service_bank_test.model.Recommendation;
import pro.sky.service_bank_test.service.RecommendationService;

import java.util.Optional;
import java.util.UUID;

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

    public RecommendationByUserTelegram byUser(String userName) {
        try {
            return jdbcTemplate.queryForObject(NAME_SQL,
                    new Object[]{userName},
                    (rs, rowNum) -> new RecommendationByUserTelegram(
                            rs.getObject("ID", UUID.class),
                            rs.getString("FIRST_NAME"),
                            rs.getString("LAST_NAME")
                            )
            );
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("User not found: " + userName, e);
        }
    }

    public Optional<Recommendation> recommendations(RecommendationByUserTelegram user) {
        return recommendationService.findRecommendationFromUser(user.getUserId());
    }
}
