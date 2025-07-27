package com.akash.cardInventory.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IdspProfileRequest {

    private String profile;
    private String partnerCode;
    private String version;
    private String fs;
    private String configurator;
    private String edd  ;
    private String comment  ;
    private String type  ;
}
