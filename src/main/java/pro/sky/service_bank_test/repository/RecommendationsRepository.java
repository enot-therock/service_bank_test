package pro.sky.service_bank_test.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static pro.sky.service_bank_test.util.UtilVariableSQL.*;

@Repository
public class RecommendationsRepository {

    private final JdbcTemplate jdbcTemplate;

    public RecommendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UUID> findUsersFromInvest() {
        return jdbcTemplate.queryForList(INVEST_SQL, UUID.class);
    }

    public List<UUID> findUserFromSaving() {
        return jdbcTemplate.queryForList(SAVING_SQL, UUID.class);
    }

    public List<UUID> findUsersFromCredit() {
        return jdbcTemplate.queryForList(CREDIT_SQL, UUID.class);
    }
}
