package com.akash.cardInventory.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardDescription;

    private int tri;
    private int twoFF;
    private int threeFF;
    private int fourFF;
    private int mff2;
    private int b4;

    private int totalQuantity;
}
