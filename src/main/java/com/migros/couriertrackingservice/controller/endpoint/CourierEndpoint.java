package com.migros.couriertrackingservice.controller.endpoint;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CourierEndpoint {
    public static final String BASE_URI = "courier";
    public static final String UPDATE_COURIER_LOCATION = "/{id}/location";
    public static final String GET_TOTAL_DISTANCE = "/{id}/totalDistance";
    public static final String GET_STORE_ENTRANCE_LOGS = "/{id}/logs";

}
