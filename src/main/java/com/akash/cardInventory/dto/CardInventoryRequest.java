package com.akash.cardInventory.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardInventoryRequest {

    private String cardDescription;

    private int tri;
    private int twoFF;
    private int threeFF;
    private int fourFF;
    private int mff2;
    private int b4;
}
