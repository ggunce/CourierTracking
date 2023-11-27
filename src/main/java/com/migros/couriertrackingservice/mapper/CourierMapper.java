package com.migros.couriertrackingservice.mapper;

import com.migros.couriertrackingservice.model.dto.Courier;
import com.migros.couriertrackingservice.model.entity.CourierEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourierMapper {

    @Mapping(target = "location.latitude", source = "latitude")
    @Mapping(target = "location.longitude", source = "longitude")
    Courier map(CourierEntity entity);

    @Mapping(target = "latitude", expression = "java(courier.getLocation().getLatitude())")
    @Mapping(target = "longitude", expression = "java(courier.getLocation().getLongitude())")
    CourierEntity map(Courier courier);
}
