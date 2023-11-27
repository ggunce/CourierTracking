package com.migros.couriertrackingservice.repository;

import com.migros.couriertrackingservice.model.entity.CourierLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface CourierLogRepository extends JpaRepository<CourierLogEntity, UUID> {

    List<CourierLogEntity> findAllByCourierIdAndStoreIdAndLogDateGreaterThanEqual(UUID courierId, Long storeId, OffsetDateTime date);
    List<CourierLogEntity> findByCourierId(UUID courierId);
}
