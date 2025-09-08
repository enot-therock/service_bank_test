package pro.sky.service_bank_test.configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CaffeineConfig {

    @Value("${caffeine.maximumSize}")
    private int maximumSize;

    @Value("${caffeine.endAccess}")
    private int endAccess;

    @Bean(name = "investCache")
    public Cache<String, List<UUID>> investCache() {
        return Caffeine.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterAccess(endAccess, TimeUnit.MINUTES)
                .recordStats()
                .build();
    }

    @Bean(name = "savingCache")
    public Cache<String, List<UUID>> savingCache() {
        return Caffeine.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterAccess(endAccess, TimeUnit.MINUTES)
                .recordStats()
                .build();
    }

    @Bean(name = "creditCache")
    public Cache<String, List<UUID>> creditCache() {
        return Caffeine.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterAccess(endAccess, TimeUnit.MINUTES)
                .recordStats()
                .build();
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("investCache"),
                new ConcurrentMapCache("savingCache"),
                new ConcurrentMapCache("creditCache")
        ));
        return cacheManager;
    }
}
