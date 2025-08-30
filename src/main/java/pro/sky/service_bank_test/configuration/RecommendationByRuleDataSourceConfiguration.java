package pro.sky.service_bank_test.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class RecommendationByRuleDataSourceConfiguration {

    @Primary
    @Bean(name = "defaultDataSource")
    public DataSource defaultDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder()
                .url("jdbc:postgresql://localhost:5432/service_bank")
                .username("users")
                .password("123456")
                .build();
    }

    @Primary
    @Bean(name = "dynamicRepository")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }
}
