package com.migros.couriertrackingservice.service.impl;

import com.migros.couriertrackingservice.model.dto.Store;
import com.migros.couriertrackingservice.service.StoreCacheService;
import com.migros.couriertrackingservice.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreCacheServiceImpl implements StoreCacheService {

    private final StoreService storeService;

    @Override
    @Cacheable(value = "stores", unless="#result.empty")
    public List<Store> getAllStores() {
        return storeService.findAll();
    }
}
