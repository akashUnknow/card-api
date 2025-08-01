package com.akash.cardInventory.service;

import com.akash.cardInventory.dto.BapMonthlyCount;
import com.akash.cardInventory.dto.BapProfileRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface BapService {

    ResponseEntity<?> addProfile(BapProfileRequest request);

    ResponseEntity<?> searchByOrderNo(String orderNo);

    ResponseEntity<?> updateByOrderNo(BapProfileRequest request);

    List<BapMonthlyCount> countByDateRange(LocalDate startDate, LocalDate endDate);

    List<BapMonthlyCount> countByMonthForYear(int year);
}
