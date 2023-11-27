package com.migros.couriertrackingservice.mapper;

import com.migros.couriertrackingservice.model.dto.CourierLocationLog;
import com.migros.couriertrackingservice.model.entity.CourierLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourierLogMapper {

    @Mapping(target = "location.latitude", source = "latitude")
    @Mapping(target = "location.longitude", source = "longitude")
    CourierLocationLog map(CourierLogEntity entity);

    List<CourierLocationLog> map(List<CourierLogEntity> entityList);
}
