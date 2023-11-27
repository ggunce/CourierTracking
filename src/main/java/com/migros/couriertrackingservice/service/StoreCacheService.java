package com.migros.couriertrackingservice.service;

import com.migros.couriertrackingservice.model.dto.Store;

import java.util.List;

public interface StoreCacheService {
    List<Store> getAllStores();
}
