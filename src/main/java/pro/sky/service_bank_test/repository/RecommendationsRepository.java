package pro.sky.service_bank_test.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static pro.sky.service_bank_test.util.UtilVariableSQL.*;

@Repository
@CacheConfig(cacheNames = {"investCache", "savingCache", "creditCache"})
public class RecommendationsRepository {

    private final JdbcTemplate jdbcTemplate;

    public RecommendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Cacheable(value = "investCache", key = "'investUsers'")
    public List<UUID> findUsersFromInvest() {
        return jdbcTemplate.queryForList(INVEST_SQL, UUID.class);
    }

    @Cacheable(value = "savingCache", key = "'savingUsers'")
    public List<UUID> findUserFromSaving() {
        return jdbcTemplate.queryForList(SAVING_SQL, UUID.class);
    }

    @Cacheable(value = "creditCache", key = "'creditUsers'")
    public List<UUID> findUsersFromCredit() {
        return jdbcTemplate.queryForList(CREDIT_SQL, UUID.class);
    }
}
