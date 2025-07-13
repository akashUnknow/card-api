package com.akash.cardInventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder

public class DgInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String month;
    private String orderNo;
    private String gdIndia;
    private String salesSub;
    private String orderDate;
    private String customerDelivery;
    private String schedule;
   private String  profileData;
   private String  lagTime;
    private String customer;
    private String profile;
    private String reference;
   private String  orderDetails;
    private String status;
    private String qty;
    private String factory;
    private String processor;
    private String validator;
    private String ARFF;
    private String response;
    private String userName;
}
