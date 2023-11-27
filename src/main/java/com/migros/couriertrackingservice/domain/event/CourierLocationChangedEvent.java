package com.migros.couriertrackingservice.domain.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CourierLocationChangedEvent extends ApplicationEvent {

    private UUID courierId;

    public CourierLocationChangedEvent(final Object source,
                                       final UUID courierId) {
        super(source);
        this.courierId = courierId;
    }

}
