package com.akash.cardInventory.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CardInventoryResponse {
    private String cardDescription;
    private int tri;
    private int twoFF;
    private int threeFF;
    private int fourFF;
    private int mff2;
    private int b4;
    private int totalQuantity;
}
