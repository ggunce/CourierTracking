package com.migros.couriertrackingservice.domain.listener;

import com.migros.couriertrackingservice.domain.event.CourierLocationChangedEvent;
import com.migros.couriertrackingservice.service.CourierTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourierLocationChangedEventListener {

    private final CourierTrackingService courierTrackingService;

    @EventListener
    public void handle(CourierLocationChangedEvent event) {
        courierTrackingService.applyCourierLocationChanges(event.getCourierId());
    }
}
