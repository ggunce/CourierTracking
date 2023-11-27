package com.migros.couriertrackingservice.service;

import java.util.UUID;

public interface CourierTrackingService {
    void applyCourierLocationChanges(UUID courierId);
}
