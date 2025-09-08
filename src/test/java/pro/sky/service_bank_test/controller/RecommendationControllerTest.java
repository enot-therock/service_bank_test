package pro.sky.service_bank_test.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.service_bank_test.model.Recommendation;
import pro.sky.service_bank_test.service.RecommendationService;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecommendationControllerTest {

    @Mock
    private RecommendationService recommendationService;

    @InjectMocks
    private RecommendationController recommendationController;

    @Test
    void getRecommendations_WhenRecommendationExists_ShouldReturnRecommendation() {

        UUID userId = UUID.randomUUID();
        Recommendation expectedRecommendation = new Recommendation(UUID.randomUUID(),
                "Name", "Text");

        when(recommendationService.findRecommendationFromUser(userId))
                .thenReturn(Optional.of(expectedRecommendation));

        Optional<Recommendation> result = recommendationController.getRecommendations(userId);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(expectedRecommendation);
        verify(recommendationService).findRecommendationFromUser(userId);
    }

    @Test
    void getRecommendations_WhenRecommendationNotExists_ShouldReturnEmptyOptional() {

        UUID userId = UUID.randomUUID();

        when(recommendationService.findRecommendationFromUser(userId))
                .thenReturn(Optional.empty());

        Optional<Recommendation> result = recommendationController.getRecommendations(userId);

        assertThat(result).isEmpty();
        verify(recommendationService).findRecommendationFromUser(userId);
    }
}
