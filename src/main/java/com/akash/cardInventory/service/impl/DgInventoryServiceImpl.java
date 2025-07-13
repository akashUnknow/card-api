package com.akash.cardInventory.service.impl;

import com.akash.cardInventory.dto.DgInventoryMonthlyCount;
import com.akash.cardInventory.dto.DgInventoryRequest;
import com.akash.cardInventory.dto.LoginActivityRequest;
import com.akash.cardInventory.entity.DgInventory;
import com.akash.cardInventory.entity.Login;
import com.akash.cardInventory.repository.DgInventoryRepository;
import com.akash.cardInventory.repository.LoginRepository;
import com.akash.cardInventory.service.DgInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DgInventoryServiceImpl implements DgInventoryService {
    @Autowired
    private DgInventoryRepository repository;

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public ResponseEntity<?> createDgInventory(DgInventoryRequest request) {

        // Check for existing by gdIndia
        List<DgInventory> byGdIndia = repository.findAllByGdIndia(request.getGdIndia());
        if (!byGdIndia.isEmpty()) {
//            return ResponseEntity.badRequest().body("Duplicate found: gdIndia = " + request.getGdIndia());
            return  ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("message", "Duplicate found: gdIndia"));
        }

        // If no duplicate found, proceed to save
        DgInventory newEntry = DgInventory.builder()
                .month(request.getMonth())
                .orderNo(request.getOrderNo())
                .gdIndia(request.getGdIndia())
                .salesSub(request.getSalesSub())
                .orderDate(request.getOrderDate())
                .customerDelivery(request.getCustomerDelivery())
                .schedule(request.getSchedule())
                .profileData(request.getProfileData())
                .lagTime(request.getLagTime())
                .customer(request.getCustomer())
                .profile(request.getProfile())
                .reference(request.getReference())
                .orderDetails(request.getOrderDetails())
                .status(request.getStatus())
                .qty(request.getQty())
                .factory(request.getFactory())
                .processor(request.getProcessor())
                .validator(request.getValidator())
                .userName(request.getUserName())
                .ARFF(request.getARFF())
                .response(request.getResponse())
                .build();

        DgInventory saved = repository.save(newEntry);
        return ResponseEntity.ok(saved);
    }

    @Override
    public List<Login> LoginActivity(LoginActivityRequest request) {
        return loginRepository.findAllByuserName(request.getUserName());
    }


    @Override
    public ResponseEntity<?> serachDgGdIndia(String gdIndia) {
        DgInventory byGdIndia = repository.findRowByGdIndia(gdIndia);

        if (byGdIndia != null) {
            return ResponseEntity.ok(byGdIndia);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "GDINDIA Internal NOT found"));
        }
    }

    @Override
    public ResponseEntity<?> updateByGdIndia(DgInventoryRequest request) {
        DgInventory existing = repository.findRowByGdIndia(request.getGdIndia());

        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Record with GDINDIA not found"));
        }

        // Update fields
        existing.setMonth(request.getMonth());
        existing.setOrderNo(request.getOrderNo());
        existing.setSalesSub(request.getSalesSub());
        existing.setOrderDate(request.getOrderDate());
        existing.setCustomerDelivery(request.getCustomerDelivery());
        existing.setSchedule(request.getSchedule());
        existing.setProfileData(request.getProfileData());
        existing.setLagTime(request.getLagTime());
        existing.setCustomer(request.getCustomer());
        existing.setProfile(request.getProfile());
        existing.setReference(request.getReference());
        existing.setOrderDetails(request.getOrderDetails());
        existing.setStatus(request.getStatus());
        existing.setQty(request.getQty());
        existing.setFactory(request.getFactory());
        existing.setProcessor(request.getProcessor());
        existing.setValidator(request.getValidator());
        existing.setARFF(request.getARFF());
        existing.setResponse(request.getResponse());
        existing.setUserName(request.getUserName());

        repository.save(existing);

        return ResponseEntity.ok(existing);
    }

    @Override
    public List<DgInventoryMonthlyCount> countByMonthForYear(int year) {
        List<Object[]> result = repository.countByMonthForYearNative(year);

        // Convert Object[] to DgInventoryMonthlyCount DTO
        return result.stream().map(row -> {
            String monthName = ((String) row[0]).trim(); // sometimes padded with spaces
            Long count = ((Number) row[1]).longValue();
            return new DgInventoryMonthlyCount(monthName, count);
        }).toList();
    }
    public List<DgInventoryMonthlyCount> countByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Object[]> results = repository.countByDateRange(startDate, endDate);
        return results.stream()
                .map(obj -> new DgInventoryMonthlyCount((String) obj[0], ((Number) obj[1]).longValue()))
                .collect(Collectors.toList());
    }


}
