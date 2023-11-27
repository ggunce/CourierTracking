package com.migros.couriertrackingservice.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Store {

    private Long id;
    private String name;
    private Location location;
}
