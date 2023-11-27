package com.migros.couriertrackingservice.builder;

import com.migros.couriertrackingservice.model.dto.Courier;
import com.migros.couriertrackingservice.model.dto.Location;
import com.migros.couriertrackingservice.model.dto.Store;
import com.migros.couriertrackingservice.model.entity.CourierEntity;
import com.migros.couriertrackingservice.model.request.CreateCourierRequest;
import com.migros.couriertrackingservice.model.request.CreateStoresRequest;
import com.migros.couriertrackingservice.model.request.StoreRequest;
import com.migros.couriertrackingservice.model.request.UpdateCourierLocationRequest;
import com.migros.couriertrackingservice.model.response.CreateStoresResponse;

import java.util.List;
import java.util.UUID;

public class ObjectBuilder {

    public static CreateCourierRequest buildCreateCourierRequest(String name, double lat, double lon, double distance) {
        return CreateCourierRequest.builder()
                .name(name)
                .currentLocation(buildLocation(lat, lon))
                .totalTravelDistance(distance)
                .build();
    }

    public static Location buildLocation(double lat, double lon) {
        return Location.builder()
                .latitude(lat)
                .longitude(lon)
                .build();
    }

    public static Courier buildCourier(UUID id, String name, double lat, double lon, double distance) {
        return Courier.builder()
                .id(id)
                .name(name)
                .location(buildLocation(lat, lon))
                .totalTravelDistance(distance)
                .build();
    }

    public static StoreRequest buildStoreRequest(String name, double lat, double lon) {
        return StoreRequest.builder()
                .name(name)
                .lat(lat)
                .lon(lon)
                .build();
    }

    public static CreateStoresRequest buildCreateStoresRequest(String name, double lat, double lon) {
        return CreateStoresRequest.builder()
                .storeRequestList(List.of(buildStoreRequest(name, lat, lon)))
                .build();
    }

    public static Store buildStore(Long id, String name, double lat, double lon) {
        return Store.builder()
                .id(id)
                .location(buildLocation(lat, lon))
                .name(name)
                .build();
    }

    public static CreateStoresResponse buildCreateStoresResponse(Long id, String name, double lat, double lon) {
        return CreateStoresResponse.builder()
                .storeList(List.of(buildStore(id, name, lat, lon)))
                .build();
    }

    public static CourierEntity buildCourierEntity(UUID id, String name, double lat, double lon, double distance) {
        return CourierEntity.builder()
                .id(id)
                .name(name)
                .latitude(lat)
                .longitude(lon)
                .totalTravelDistance(distance)
                .build();
    }

    public static UpdateCourierLocationRequest buildUpdateCourierLocationRequest(double lat, double lon) {
        return UpdateCourierLocationRequest.builder()
                .latitude(lat)
                .longitude(lon)
                .build();
    }
}
