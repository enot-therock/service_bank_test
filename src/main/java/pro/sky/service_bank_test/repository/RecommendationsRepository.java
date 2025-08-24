package pro.sky.service_bank_test.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pro.sky.service_bank_test.model.Recommendation;

import java.util.List;
import java.util.UUID;

@Repository
public class RecommendationsRepository {

    private final JdbcTemplate jdbcTemplate;

    public RecommendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UUID> findUsers() {
        String sql =
                "SELECT USER_ID FROM transaction WHERE USER_ID IN " +
                        "(SELECT USER_ID FROM transaction WHERE type = 'DEBIT' GROUP BY USER_ID)" +
                        "AND USER_ID NOT IN " +
                        "(SELECT USER_ID FROM transaction WHERE type = 'INSERT')" +
                        "AND USER_ID IN " +
                        "(SELECT USER_ID FROM transaction WHERE type = 'SAVING' " +
                        "GROUP BY USER_ID HAVING SUM(amount) > 1000)" +
                        "GROUP BY USER_ID";
        return jdbcTemplate.queryForList(sql, UUID.class);
    }
}
