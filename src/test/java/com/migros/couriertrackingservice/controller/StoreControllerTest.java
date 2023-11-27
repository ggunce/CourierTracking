package com.migros.couriertrackingservice.controller;

import com.migros.couriertrackingservice.builder.DataBuilder;
import com.migros.couriertrackingservice.builder.ObjectBuilder;
import com.migros.couriertrackingservice.model.request.CreateStoresRequest;
import com.migros.couriertrackingservice.model.response.CreateStoresResponse;
import com.migros.couriertrackingservice.service.StoreService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StoreControllerTest {

    @Mock
    private StoreService storeService;
    @Autowired
    private StoreController controller;

    @Test
    void givenRequestValid_whenCreate_thenSuccess() {
        final CreateStoresRequest request = ObjectBuilder.buildCreateStoresRequest(DataBuilder.storeName1,
                DataBuilder.storeLat1, DataBuilder.storeLon1);
        final CreateStoresResponse store = ObjectBuilder.buildCreateStoresResponse(DataBuilder.storeId ,
                DataBuilder.storeName1, DataBuilder.storeLat1, DataBuilder.storeLon1);

        when(storeService.createStores(request)).thenReturn(store);

        ResponseEntity<CreateStoresResponse> response = controller.create(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertThat(response).isNotNull();
    }

    @Test
    void givenRequestValid_whenValidated_thenNoValidationError() {
        final CreateStoresRequest request = ObjectBuilder.buildCreateStoresRequest(DataBuilder.storeName1,
                DataBuilder.storeLat1, DataBuilder.storeLon1);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<CreateStoresRequest>> constraintViolations =
                validator.validate(request);

        assertThat(constraintViolations.size()).isZero();
    }

    @Test
    void givenRequestInvalid_whenValidated_thenValidationError() {
        final CreateStoresRequest request = ObjectBuilder.buildCreateStoresRequest(null,
                DataBuilder.storeLat1, DataBuilder.storeLon1);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<CreateStoresRequest>> constraintViolations =
                validator.validate(request);

        assertThat(constraintViolations.size()).isNotZero();
    }
}
