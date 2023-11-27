package com.migros.couriertrackingservice.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class CourierLocationLog {

    private UUID courierId;
    private Long storeId;
    private OffsetDateTime entranceDate;
    private Location location;
}
