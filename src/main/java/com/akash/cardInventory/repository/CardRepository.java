package com.akash.cardInventory.repository;


import com.akash.cardInventory.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    // Custom query method
    @Query("SELECT COALESCE(SUM(c.quantity), 0) FROM Card c WHERE c.description = :description AND c.formFactor = :formFactor")
    long getQuantityByDescriptionAndFormFactor(@Param("description") String description,
                                               @Param("formFactor") String formFactor);



    Optional<Card> findByDescriptionAndFormFactor(String description, String formFactor);
    List<Card> findAllByDescriptionAndFormFactor(String description, String formFactor);



}
