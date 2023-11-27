package com.migros.couriertrackingservice.controller;

import com.migros.couriertrackingservice.controller.endpoint.CourierEndpoint;
import com.migros.couriertrackingservice.model.dto.Courier;
import com.migros.couriertrackingservice.model.dto.CourierLocationLog;
import com.migros.couriertrackingservice.model.request.CreateCourierRequest;
import com.migros.couriertrackingservice.model.request.UpdateCourierLocationRequest;
import com.migros.couriertrackingservice.model.response.CourierStoreLogsResponse;
import com.migros.couriertrackingservice.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import static com.migros.couriertrackingservice.controller.endpoint.CourierEndpoint.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(CourierEndpoint.BASE_URI)
public class CourierController {
    private final CourierService courierService;

    @PostMapping
    public ResponseEntity<Courier> create(@Valid @RequestBody final CreateCourierRequest request) {
        return new ResponseEntity<>(courierService.createCourier(request), CREATED);
    }

    @PatchMapping(UPDATE_COURIER_LOCATION)
    public ResponseEntity<Void> updateLocation(@PathVariable final UUID id,
                                                 @Valid @RequestBody final UpdateCourierLocationRequest request) {
        courierService.changeCourierLocation(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(GET_TOTAL_DISTANCE)
    public ResponseEntity<Double> getTotalTravelDistance(@PathVariable final UUID id) {
        return new ResponseEntity<>(courierService.getTotalTravelDistance(id), OK);
    }

    @GetMapping(GET_STORE_ENTRANCE_LOGS)
    public ResponseEntity<CourierStoreLogsResponse> getCourierStoreEntranceLogs(@PathVariable final UUID id) {
        return new ResponseEntity<>(courierService.getCourierStoreEntranceLogs(id), OK);
    }
}
