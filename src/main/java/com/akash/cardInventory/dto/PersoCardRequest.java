package com.akash.cardInventory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersoCardRequest {
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
}
