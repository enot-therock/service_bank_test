package pro.sky.service_bank_test.service.cacheService;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.stereotype.Service;
import pro.sky.service_bank_test.repository.RecommendationsRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CacheService {

    private final RecommendationsRepository recommendationsRepository;
    private final Cache<String, List<UUID>> investCache;
    private final Cache<String, List<UUID>> savingCache;
    private final Cache<String, List<UUID>> creditCache;

    public CacheService(RecommendationsRepository recommendationsRepository,
                        Cache<String, List<UUID>> investCache,
                        Cache<String, List<UUID>> savingCache,
                        Cache<String, List<UUID>> creditCache) {
        this.recommendationsRepository = recommendationsRepository;
        this.investCache = investCache;
        this.savingCache = savingCache;
        this.creditCache = creditCache;
    }

    public List<UUID> getUsersFromInvest() {
        return investCache.get("investUsers", key -> recommendationsRepository.findUsersFromInvest());
    }

    public List<UUID> getUsersFromSaving() {
        return savingCache.get("savingUsers", key -> recommendationsRepository.findUserFromSaving());
    }

    public List<UUID> getUsersFromCredit() {
        return creditCache.get("creditUsers", key -> recommendationsRepository.findUsersFromCredit());
    }

    public void evictInvestCache() {
        investCache.invalidate("investUsers");
    }

    public void evictSavingCache() {
        savingCache.invalidate("savingUsers");
    }

    public void evictCreditCache() {
        creditCache.invalidate("creditUsers");
    }
}
