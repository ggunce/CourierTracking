package com.migros.couriertrackingservice.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreRequest {
    @NotEmpty
    private String name;
    @NotNull
    private double lat;
    @NotNull
    private double lon;
}
