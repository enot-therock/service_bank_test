package pro.sky.service_bank_test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.service_bank_test.model.Recommendation;
import pro.sky.service_bank_test.service.RecommendationService;

import java.util.List;
import java.util.UUID;

@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{user_id}")
    public List<Recommendation> getRecommendations(@PathVariable UUID user_id) {
        return recommendationService.findRecommendationFromUser(user_id);
    }

}
