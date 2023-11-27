package com.migros.couriertrackingservice.service;

import com.migros.couriertrackingservice.domain.exception.StoreRequestNotValidException;
import com.migros.couriertrackingservice.model.request.CreateStoresRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
public class StoreServiceTest {

    @Autowired
    private StoreService storeService;

    @Test
    void givenRequestInvalid_whenCreate_thenException() {
        final CreateStoresRequest request = CreateStoresRequest.builder()
                .storeRequestList(null)
                .build();
        final Throwable throwable = catchThrowable(() -> storeService.createStores(request));

        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(StoreRequestNotValidException.class);
    }

}
