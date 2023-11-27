package com.migros.couriertrackingservice.repository;

import com.migros.couriertrackingservice.model.entity.CourierEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourierRepository extends CrudRepository<CourierEntity, UUID> {

}
