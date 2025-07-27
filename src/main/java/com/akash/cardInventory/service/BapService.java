package com.akash.cardInventory.service;

import com.akash.cardInventory.dto.BapProfileRequest;
import org.springframework.http.ResponseEntity;

public interface BapService {

    ResponseEntity<?> addProfile(BapProfileRequest request);

    ResponseEntity<?> searchByOrderNo(String orderNo);

    ResponseEntity<?> updateByOrderNo(BapProfileRequest request);
}
