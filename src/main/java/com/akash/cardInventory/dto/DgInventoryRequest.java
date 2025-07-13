package com.akash.cardInventory.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DgInventoryRequest {
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
