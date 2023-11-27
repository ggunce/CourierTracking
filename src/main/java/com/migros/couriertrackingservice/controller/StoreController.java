package com.migros.couriertrackingservice.controller;

import com.migros.couriertrackingservice.controller.endpoint.StoreEndpoint;
import com.migros.couriertrackingservice.model.request.CreateStoresRequest;
import com.migros.couriertrackingservice.model.response.CreateStoresResponse;
import com.migros.couriertrackingservice.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(StoreEndpoint.BASE_URI)
public class StoreController {
    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<CreateStoresResponse> create(@Valid @RequestBody final CreateStoresRequest request) {
        return new ResponseEntity<>(storeService.createStores(request), CREATED);
    }
}
