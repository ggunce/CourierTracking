package com.migros.couriertrackingservice.service.impl;

import com.migros.couriertrackingservice.domain.event.CourierLocationChangedEvent;
import com.migros.couriertrackingservice.domain.exception.CourierNotFoundException;
import com.migros.couriertrackingservice.mapper.CourierLogMapper;
import com.migros.couriertrackingservice.mapper.CourierMapper;
import com.migros.couriertrackingservice.model.dto.Courier;
import com.migros.couriertrackingservice.model.dto.Location;
import com.migros.couriertrackingservice.model.entity.CourierEntity;
import com.migros.couriertrackingservice.model.entity.CourierLogEntity;
import com.migros.couriertrackingservice.model.request.CreateCourierRequest;
import com.migros.couriertrackingservice.model.request.UpdateCourierLocationRequest;
import com.migros.couriertrackingservice.model.response.CourierStoreLogsResponse;
import com.migros.couriertrackingservice.repository.CourierLogRepository;
import com.migros.couriertrackingservice.repository.CourierRepository;
import com.migros.couriertrackingservice.service.CourierService;
import com.migros.couriertrackingservice.util.GeolocationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {

    private final CourierRepository repository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final CourierLogRepository logRepository;
    private final CourierMapper courierMapper;
    private final CourierLogMapper logMapper;

    @Override
    public Courier createCourier(CreateCourierRequest request) {
        final Courier courier = Courier.builder()
                .name(request.getName())
                .totalTravelDistance(request.getTotalTravelDistance())
                .location(request.getCurrentLocation())
                .build();

        var entity = this.saveCourier(courier);
        courier.setId(entity.getId());
        return courier;
    }

    @Override
    @Transactional
    public void changeCourierLocation(UUID id, UpdateCourierLocationRequest request) {
        final Courier courier = this.findCourier(id);

        final Location location = Location.builder()
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();

        courier.setTotalTravelDistance(calculateTotalTravelDistance(courier, request));
        courier.setLocation(location);
        this.saveCourier(courier);
        applicationEventPublisher.publishEvent(new CourierLocationChangedEvent(this, id));
    }

    @Override
    public Courier findCourier(UUID courierId) throws CourierNotFoundException {
        Optional<CourierEntity> entity = repository.findById(courierId);
        if (entity.isEmpty()) {
            throw new CourierNotFoundException();
        }
        return courierMapper.map(entity.get());
    }

    @Override
    public Double getTotalTravelDistance(UUID courierId) {
        final Courier courierResponse = this.findCourier(courierId);
        return courierResponse.getTotalTravelDistance();
    }

    private CourierEntity saveCourier(Courier courier) {
        return repository.save(courierMapper.map(courier));
    }

    private double calculateTotalTravelDistance(final Courier courier, UpdateCourierLocationRequest request) {
        double lastTravelDistance = GeolocationUtil.getInstance().calculateDistance(courier.getLocation().getLatitude(), courier.getLocation().getLongitude(), request.getLatitude(), request.getLongitude());
        return courier.getTotalTravelDistance() + lastTravelDistance;
    }

    public CourierStoreLogsResponse getCourierStoreEntranceLogs(UUID courierId) {
        List<CourierLogEntity> entityList = logRepository.findByCourierId(courierId);
        return CourierStoreLogsResponse.builder().locationLogs(logMapper.map(entityList)).build();
    }

}
