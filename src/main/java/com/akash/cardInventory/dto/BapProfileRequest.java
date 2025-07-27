package com.akash.cardInventory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class BapProfileRequest {
    private String orderNo;
    private String sapNo;
    private String cpssNo;
    private String dgCateg;
    private String priority;
    private String profile;
    private String requestType;
    private String developer;
    private String validator;
    private String validationStatus;
    private String orderReceive;
    private String sLAscheduledFinishDate;
    private String startDate;
    private String finishDate;
    private String status;
    private String remarks;

}
