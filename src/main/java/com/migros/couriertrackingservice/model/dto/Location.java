package com.migros.couriertrackingservice.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {

    private double latitude;
    private double longitude;
}
