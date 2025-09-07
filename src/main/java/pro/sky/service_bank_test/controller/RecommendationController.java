package pro.sky.service_bank_test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.service_bank_test.model.Recommendation;
import pro.sky.service_bank_test.service.RecommendationService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommendation/{id}")
    public Optional<Recommendation> getRecommendations(@PathVariable UUID id) {
        return recommendationService.findRecommendationFromUser(id);
    }

}
