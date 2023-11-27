package com.migros.couriertrackingservice.service.impl;

import com.migros.couriertrackingservice.domain.exception.StoreRequestNotValidException;
import com.migros.couriertrackingservice.mapper.StoreMapper;
import com.migros.couriertrackingservice.model.dto.Store;
import com.migros.couriertrackingservice.model.entity.StoreEntity;
import com.migros.couriertrackingservice.model.request.CreateStoresRequest;
import com.migros.couriertrackingservice.model.response.CreateStoresResponse;
import com.migros.couriertrackingservice.repository.StoreRepository;
import com.migros.couriertrackingservice.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository repository;
    private final StoreMapper storeMapper;

    @Override
    public CreateStoresResponse createStores(CreateStoresRequest request) throws StoreRequestNotValidException{
        if(CollectionUtils.isEmpty(request.getStoreRequestList())) {
            throw new StoreRequestNotValidException();
        }
        List<StoreEntity> list = storeMapper.map(request.getStoreRequestList());

        List<Store> savedStores = storeMapper.mapEntityList(repository.saveAll(list));
        return CreateStoresResponse.builder()
                .storeList(savedStores)
                .build();
    }

    @Override
    public List<Store> findAll() {
        var stores = repository.findAll();
        List<Store> storeList = new ArrayList<>();
        stores.forEach(storeEntity ->  storeList.add(storeMapper.map(storeEntity)));

        return storeList;
    }
}
