package pro.sky.service_bank_test.controller;

import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.service_bank_test.model.Recommendation;
import pro.sky.service_bank_test.service.RecommendationService;

import javax.xml.transform.OutputKeys;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final Recommendation recommendation;

    public RecommendationController(RecommendationService recommendationService, Recommendation recommendation) {
        this.recommendationService = recommendationService;
        this.recommendation = recommendation;
    }

//    @GetMapping("/recommendation/{user_id}")
//    public ResponseEntity<Recommendation> getRecommendations(@PathVariable UUID user_id) {
//        List<ProductRecommendation> recommendations = recommendationService.findRecommendationFromUser(user_id);
//
//        Recommendation recommendation1 = new Recommendation();
//        return ResponseEntity.status(HttpStatus.OK).body(recommendation1);
//    }
//    Request: GET /recommendation/<user_id>
//    Response:
//    200 OK
//    Content-Type: application/json
//    {
//            "user_id": <user_id>,
//            "recommendations": [
//                    {"name": <имя продукта>, "id": <id продукта>, "text": "текстовое описание продукта"},
//                    ...
//        ]
//    }
}
