package com.migros.couriertrackingservice.service;

import com.migros.couriertrackingservice.model.dto.Courier;
import com.migros.couriertrackingservice.model.request.CreateCourierRequest;
import com.migros.couriertrackingservice.model.request.UpdateCourierLocationRequest;
import com.migros.couriertrackingservice.model.response.CourierStoreLogsResponse;
import java.util.UUID;

public interface CourierService {

    Courier createCourier(CreateCourierRequest request);
    void changeCourierLocation(UUID id, UpdateCourierLocationRequest request);
    Courier findCourier(UUID courierId);
    Double getTotalTravelDistance(UUID courierId);
    CourierStoreLogsResponse getCourierStoreEntranceLogs(UUID courierId);
}
