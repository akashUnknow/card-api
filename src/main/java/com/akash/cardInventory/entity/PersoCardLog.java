package com.akash.cardInventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class PersoCardLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardDescription;
    private String formFactor;
    private int usedQuantity;

    private String profile;
    private String configurator;
    private String issuedTo;
    private String customer;

    private int rst;
    private int telcaPersoTest;
    private int shredCard;

    private LocalDateTime timestamp;
}
