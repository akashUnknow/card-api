package com.akash.cardInventory.repository;

import com.akash.cardInventory.entity.IdspProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdspProfileRepo extends JpaRepository<IdspProfileEntity, Long> {
    


    List<IdspProfileEntity> findAllRowByfs(String fs);

    IdspProfileEntity findRowByfs(String fs);

    List<IdspProfileEntity> findRowBytype(String type);
}
