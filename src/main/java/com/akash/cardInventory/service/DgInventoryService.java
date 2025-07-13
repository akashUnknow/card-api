package com.akash.cardInventory.service;

import com.akash.cardInventory.dto.DgInventoryMonthlyCount;
import com.akash.cardInventory.dto.DgInventoryRequest;
import com.akash.cardInventory.dto.LoginActivityRequest;
import com.akash.cardInventory.entity.DgInventory;
import com.akash.cardInventory.entity.Login;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface DgInventoryService {

    ResponseEntity<?> createDgInventory(DgInventoryRequest request);

    List<Login> LoginActivity(LoginActivityRequest request);

    ResponseEntity<?> serachDgGdIndia(String gdIndia);

    ResponseEntity<?> updateByGdIndia(DgInventoryRequest request);


    List<DgInventoryMonthlyCount> countByMonthForYear(int year);

    List<DgInventoryMonthlyCount> countByDateRange(LocalDate startDate, LocalDate endDate);
}
