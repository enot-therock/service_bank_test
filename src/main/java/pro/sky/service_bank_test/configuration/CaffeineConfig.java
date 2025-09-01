package pro.sky.service_bank_test.configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfig {

    @Value("${caffeine.maximumSize}")
    private int maximumSize;

    @Value("${caffeine.endAccess}")
    private int endAccess;

    @Bean
    public Cache<String, Object> caffeineCache() {
        return Caffeine.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterAccess(endAccess, TimeUnit.MINUTES)
                .build();
    }

    @Bean
    public Caffeine caffeine() {
        return Caffeine.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterAccess(endAccess, TimeUnit.MINUTES);
    }
}
