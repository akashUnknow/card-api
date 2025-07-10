package com.akash.cardInventory.repository;

import com.akash.cardInventory.entity.CardInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardInventoryRepository extends JpaRepository<CardInventory, Long> {
    List<CardInventory> findAllByCardDescription(String cardDescription);

}
