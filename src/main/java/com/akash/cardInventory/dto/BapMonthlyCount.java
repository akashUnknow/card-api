package com.akash.cardInventory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BapMonthlyCount {
    private String monthName;
    private Long count;

    public BapMonthlyCount(String monthName, Long count) {
        this.monthName = monthName;
        this.count = count;
    }
}
