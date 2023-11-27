package com.migros.couriertrackingservice.service;

import com.migros.couriertrackingservice.model.dto.Store;
import com.migros.couriertrackingservice.model.request.CreateStoresRequest;
import com.migros.couriertrackingservice.model.response.CreateStoresResponse;

import java.util.List;

public interface StoreService {

    CreateStoresResponse createStores(CreateStoresRequest request);

    List<Store> findAll();
}
