package com.migros.couriertrackingservice.service.impl;

import com.migros.couriertrackingservice.model.dto.Courier;
import com.migros.couriertrackingservice.model.dto.Store;
import com.migros.couriertrackingservice.model.entity.CourierLogEntity;
import com.migros.couriertrackingservice.repository.CourierLogRepository;
import com.migros.couriertrackingservice.service.CourierService;
import com.migros.couriertrackingservice.service.CourierTrackingService;
import com.migros.couriertrackingservice.service.StoreCacheService;
import com.migros.couriertrackingservice.util.GeolocationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourierTrackingServiceImpl implements CourierTrackingService {
    private final double COURIER_NEAR_STORE_DISTANCE = 100;
    private final Integer TRACKING_MINUTE_PERIOD = 1;
    private final StoreCacheService storeCacheService;
    private final CourierLogRepository logRepository;
    private final CourierService courierService;
    @Override
    @Transactional
    public void applyCourierLocationChanges(UUID courierId) {
        final List<Store> stores = storeCacheService.getAllStores();
        final Courier courier = courierService.findCourier(courierId);

        stores.forEach(store -> {
            if(calculateDistance(courier, store) <= COURIER_NEAR_STORE_DISTANCE && isNotReEntry(courierId, store.getId())) {
                saveCourierTravelLog(courier, store.getId());
            }
        });
    }

    private double calculateDistance(Courier courier, Store store) {
        return GeolocationUtil.getInstance().calculateDistance(store.getLocation().getLatitude(), store.getLocation().getLongitude(), courier.getLocation().getLatitude(), courier.getLocation().getLongitude());
    }

    private boolean isNotReEntry(UUID courierId, Long storeId) {
        OffsetDateTime timeToCheck = OffsetDateTime.now().minusMinutes(TRACKING_MINUTE_PERIOD);
        var locationLogs = logRepository.findAllByCourierIdAndStoreIdAndLogDateGreaterThanEqual(courierId, storeId, timeToCheck);
        return locationLogs.isEmpty();
    }

    private void saveCourierTravelLog(Courier courier, Long storeId) {
        final CourierLogEntity log = CourierLogEntity.builder()
                .courierId(courier.getId())
                .storeId(storeId)
                .logDate(OffsetDateTime.now())
                .build();

        if (courier.getLocation() != null) {
            log.setLatitude(courier.getLocation().getLatitude());
            log.setLongitude(courier.getLocation().getLongitude());
        }

        logRepository.save(log);
    }

}
