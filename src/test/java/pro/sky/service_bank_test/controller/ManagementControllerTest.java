package pro.sky.service_bank_test.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import pro.sky.service_bank_test.model.Info;
import pro.sky.service_bank_test.service.cacheService.CacheService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ManagementControllerTest {

    @Mock
    private CacheService cacheService;

    @Mock
    private BuildProperties buildProperties;

    @InjectMocks
    private ManagementController managementController;

    @Test
    void getInvest_returnListOfUsersFromInvestCache() {

        List<UUID> expectedUsers = Arrays.asList(
                UUID.randomUUID(),
                UUID.randomUUID());

        when(cacheService.getUsersFromInvest()).thenReturn(expectedUsers);

        ResponseEntity<List<UUID>> response = managementController.getInvest();

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(expectedUsers);
        verify(cacheService).getUsersFromInvest();
    }

    @Test
    void getSaving_returnListOfUsersFromSavingCache() {

        List<UUID> expectedUsers = Arrays.asList(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());

        when(cacheService.getUsersFromSaving()).thenReturn(expectedUsers);

        ResponseEntity<List<UUID>> response = managementController.getSaving();

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(expectedUsers);
        verify(cacheService).getUsersFromSaving();
    }

    @Test
    void getCredit_returnListOfUsersFromCreditCache() {

        List<UUID> expectedUsers = List.of(
                UUID.randomUUID());

        when(cacheService.getUsersFromCredit()).thenReturn(expectedUsers);

        ResponseEntity<List<UUID>> response = managementController.getCredit();

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(expectedUsers);
        verify(cacheService).getUsersFromCredit();
    }

    @Test
    void clearAllCaches() {

        doNothing().when(cacheService).evictInvestCache();
        doNothing().when(cacheService).evictSavingCache();
        doNothing().when(cacheService).evictCreditCache();

        managementController.clearAllCaches();

        verify(cacheService, times(1)).evictInvestCache();
        verify(cacheService, times(1)).evictSavingCache();
        verify(cacheService, times(1)).evictCreditCache();
        verifyNoMoreInteractions(cacheService);
    }

    @Test
    void getInfo_returnInfoWithBuildProperties() {

        when(buildProperties.getName()).thenReturn("recommendation-service");
        when(buildProperties.getVersion()).thenReturn("1.0.0");

        Info expectedInfo = new Info("recommendation-service", "1.0.0");

        ResponseEntity<Info> response = managementController.getInfo();

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("recommendation-service");
        assertThat(response.getBody().getVersion()).isEqualTo("1.0.0");

        verify(buildProperties).getName();
        verify(buildProperties).getVersion();
    }
}
