package pro.sky.service_bank_test.controller;

import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.service_bank_test.model.Info;
import pro.sky.service_bank_test.service.cacheService.CacheService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/management")
public class ManagementController {

    private final CacheService cacheService;
    private final BuildProperties buildProperties;

    public ManagementController(CacheService cacheService,
                                BuildProperties buildProperties) {
        this.cacheService = cacheService;
        this.buildProperties = buildProperties;
    }

    @GetMapping("/invest")
    public ResponseEntity<List<UUID>> getInvest() {
        return ResponseEntity.ok(cacheService.getUsersFromInvest());
    }

    @GetMapping("/saving")
    public ResponseEntity<List<UUID>> getSaving() {
        return ResponseEntity.ok(cacheService.getUsersFromSaving());
    }

    @GetMapping("/credit")
    public ResponseEntity<List<UUID>> getCredit() {
        return ResponseEntity.ok(cacheService.getUsersFromCredit());
    }

    @PostMapping("/clear-caches")
    public ResponseEntity<String> clearAllCaches() {
        cacheService.evictInvestCache();
        cacheService.evictSavingCache();
        cacheService.evictCreditCache();

        return ResponseEntity.ok("All caches cleared");
    }

    @GetMapping("/info")
    public ResponseEntity<Info> getInfo() {
        Info info = new Info();
        info.setName(buildProperties.getName());
        info.setVersion(buildProperties.getVersion());

        return ResponseEntity.ok(info);
    }
}
