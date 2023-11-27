package com.migros.couriertrackingservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courier_log", indexes = {
        @Index(name = "idx_courierId_storeId_logDate", columnList = "courier_id, store_id, log_date"),
        @Index(name = "idx_courierId", columnList = "courier_id")
})
public class CourierLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "courier_id", nullable = false)
    private UUID courierId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @Column(name = "log_date", nullable = false)
    private OffsetDateTime logDate;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;
}
