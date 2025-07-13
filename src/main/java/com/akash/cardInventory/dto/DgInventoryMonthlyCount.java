package com.akash.cardInventory.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class DgInventoryMonthlyCount {
    private String monthName;
    private Long count;

    public DgInventoryMonthlyCount(String monthName, Long count) {
        this.monthName = monthName;
        this.count = count;
    }

    // Optionally add getters, setters, toString, etc.
}

