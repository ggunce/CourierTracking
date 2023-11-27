package com.migros.couriertrackingservice.mapper;

import com.migros.couriertrackingservice.model.dto.Store;
import com.migros.couriertrackingservice.model.entity.StoreEntity;
import com.migros.couriertrackingservice.model.request.StoreRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreEntity map(StoreRequest store);

    List<StoreEntity> map(List<StoreRequest> store);

    @Mapping(target = "location.latitude", source = "lat")
    @Mapping(target = "location.longitude", source = "lon")
    Store map(StoreEntity store);

    List<Store> mapEntityList(Iterable<StoreEntity> storeEntityList);
}
