package com.akash.cardInventory.repository;

import com.akash.cardInventory.entity.BapPRofileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BapServiceRepo extends JpaRepository<BapPRofileEntity,Long> {
    List<BapPRofileEntity> findAllRowByorderNo(String orderNo);

    BapPRofileEntity findRowByOrderNo(String orderNo);
}
