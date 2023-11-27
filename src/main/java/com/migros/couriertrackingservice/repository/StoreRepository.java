package com.migros.couriertrackingservice.repository;

import com.migros.couriertrackingservice.model.entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<StoreEntity, Long> {

}
