package com.migros.couriertrackingservice.controller;

import com.migros.couriertrackingservice.builder.DataBuilder;
import com.migros.couriertrackingservice.builder.ObjectBuilder;
import com.migros.couriertrackingservice.model.dto.Courier;
import com.migros.couriertrackingservice.model.request.CreateCourierRequest;
import com.migros.couriertrackingservice.service.CourierService;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourierControllerTest {

    @Mock
    private CourierService courierService;
    @Autowired
    private CourierController courierController;

    @Test
    void givenRequestValid_whenCreate_thenSuccess() {
        final CreateCourierRequest request = ObjectBuilder.buildCreateCourierRequest(DataBuilder.name1,
                DataBuilder.lat1, DataBuilder.lon1, DataBuilder.distance1);
        final Courier courier = ObjectBuilder.buildCourier(UUID.randomUUID(),
                DataBuilder.name1, DataBuilder.lat1, DataBuilder.lon1, DataBuilder.distance1);

        when(courierService.createCourier(request)).thenReturn(courier);

        ResponseEntity<Courier> response = courierController.create(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertThat(response).isNotNull();
    }

    @Test
    void givenRequestInvalid_whenValidated_thenValidationError() {
        final CreateCourierRequest request = ObjectBuilder.buildCreateCourierRequest(null,
                DataBuilder.lat1, DataBuilder.lon1, DataBuilder.distance1);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<CreateCourierRequest>> constraintViolations =
                validator.validate(request);

        assertThat(constraintViolations.size()).isNotZero();
    }

}
