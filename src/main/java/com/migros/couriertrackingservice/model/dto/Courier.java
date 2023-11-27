package com.migros.couriertrackingservice.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode
public class Courier {

    private UUID id;
    private String name;
    private double totalTravelDistance;
    private Location location;
}
