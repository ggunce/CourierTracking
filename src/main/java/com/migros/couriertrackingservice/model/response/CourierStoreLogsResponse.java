package com.migros.couriertrackingservice.model.response;

import com.migros.couriertrackingservice.model.dto.CourierLocationLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourierStoreLogsResponse {

    private List<CourierLocationLog> locationLogs;
}
