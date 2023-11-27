package com.migros.couriertrackingservice.service;

import com.migros.couriertrackingservice.builder.DataBuilder;
import com.migros.couriertrackingservice.builder.ObjectBuilder;
import com.migros.couriertrackingservice.domain.exception.CourierNotFoundException;
import com.migros.couriertrackingservice.model.dto.Courier;
import com.migros.couriertrackingservice.model.dto.Location;
import com.migros.couriertrackingservice.model.entity.CourierEntity;
import com.migros.couriertrackingservice.model.request.CreateCourierRequest;
import com.migros.couriertrackingservice.model.request.UpdateCourierLocationRequest;
import com.migros.couriertrackingservice.repository.CourierRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CourierServiceTest {

    @Autowired
    private CourierService courierService;
    @MockBean
    private CourierRepository repository;

    @Test
    void givenIDInvalid_whenFind_thenException() {
        final Throwable throwable = catchThrowable(() -> courierService.findCourier(UUID.randomUUID()));

        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(CourierNotFoundException.class);
    }

    @Test
    void givenIDValid_whenFind_thenSuccess() {
        final UUID id = UUID.randomUUID();
        final CourierEntity courier = ObjectBuilder.buildCourierEntity(id,
                DataBuilder.name1, DataBuilder.lat1, DataBuilder.lon1, DataBuilder.distance1);
        final Optional<CourierEntity> optionalCourierEntity = Optional.of(courier);

        when(repository.findById(any(UUID.class))).thenReturn(optionalCourierEntity);
        Courier response = courierService.findCourier(id);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(courier.getName());

        final Location locationResponse = response.getLocation();
        assertThat(locationResponse).isNotNull();
        assertThat(locationResponse.getLongitude()).isEqualTo(DataBuilder.lon1);
        assertThat(locationResponse.getLatitude()).isEqualTo(DataBuilder.lat1);
    }

    @Test
    void givenRequestValid_whenCreate_thenSuccess() {
        final CreateCourierRequest request = ObjectBuilder.buildCreateCourierRequest(DataBuilder.name1,
                DataBuilder.lat1, DataBuilder.lon1, DataBuilder.distance1);
        final CourierEntity courier = ObjectBuilder.buildCourierEntity(UUID.randomUUID(),
                DataBuilder.name1, DataBuilder.lat1, DataBuilder.lon1, DataBuilder.distance1);

        when(repository.save(any())).thenReturn(courier);

        Courier response = courierService.createCourier(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getTotalTravelDistance()).isEqualTo(request.getTotalTravelDistance());

        final Location locationResponse = response.getLocation();
        assertThat(locationResponse.getLatitude()).isEqualTo(DataBuilder.lat1);
        assertThat(locationResponse.getLongitude()).isEqualTo(DataBuilder.lon1);
    }

    @Test
    void givenIdInvalid_whenChangeLocation_thenException() {
        final UpdateCourierLocationRequest request = ObjectBuilder.buildUpdateCourierLocationRequest(DataBuilder.lat1, DataBuilder.lon1);
        final UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        final Throwable throwable = catchThrowable(() -> courierService.changeCourierLocation(id, request));

        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(CourierNotFoundException.class);
    }

}
