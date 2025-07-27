package com.akash.cardInventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class BapPRofileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
