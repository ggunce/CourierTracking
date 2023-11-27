package com.migros.couriertrackingservice.model.request;

import com.migros.couriertrackingservice.model.dto.Location;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourierRequest {
    @NotEmpty
    private String name;
    @NotNull
    private Location currentLocation;
    @Min(value = 0)
    private double totalTravelDistance;
}
