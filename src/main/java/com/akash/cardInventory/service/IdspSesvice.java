package com.akash.cardInventory.service;

import com.akash.cardInventory.dto.IdspProfileRequest;
import org.springframework.http.ResponseEntity;

public interface IdspSesvice {
    ResponseEntity<?> addProfile(IdspProfileRequest request);

    ResponseEntity<?> searchByfs(String fs);

    ResponseEntity<?> searchByType(String type);

    ResponseEntity<?> updateByfs(IdspProfileRequest request);
}
