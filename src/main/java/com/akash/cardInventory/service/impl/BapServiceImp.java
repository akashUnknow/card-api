package com.akash.cardInventory.service.impl;

import com.akash.cardInventory.dto.BapMonthlyCount;
import com.akash.cardInventory.dto.BapProfileRequest;
import com.akash.cardInventory.dto.DgInventoryMonthlyCount;
import com.akash.cardInventory.entity.BapPRofileEntity;
import com.akash.cardInventory.entity.IdspProfileEntity;
import com.akash.cardInventory.repository.BapServiceRepo;
import com.akash.cardInventory.service.BapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class BapServiceImp implements BapService {

    private final BapServiceRepo repo;

    @Autowired
    public BapServiceImp(BapServiceRepo repo){
        this.repo=repo;
    }

    @Override
    public ResponseEntity<?> addProfile(BapProfileRequest request) {
       List<BapPRofileEntity> orderNo=repo.findAllRowByorderNo(request.getOrderNo());
       if(!orderNo.isEmpty()){
           return ResponseEntity
          .status(HttpStatus.NOT_ACCEPTABLE)
                   .body(Map.of("message", "Duplicate found: orderNo"));
       }
       BapPRofileEntity newEntity=BapPRofileEntity.builder()
               .orderNo(request.getOrderNo())
               .sapNo(request.getSapNo())
               .cpssNo(request.getCpssNo())
               .dgCateg(request.getDgCateg())
               .priority(request.getPriority())
               .profile(request.getProfile())
               .requestType(request.getRequestType())
               .developer(request.getDeveloper())
               .validator(request.getValidator())
               .validationStatus(request.getValidationStatus())
               .orderReceive(request.getOrderReceive())
               .sLAscheduledFinishDate(request.getSLAscheduledFinishDate())
               .startDate(request.getStartDate())
               .finishDate(request.getFinishDate())
               .status(request.getStatus())
               .remarks(request.getRemarks())
               .build();
       BapPRofileEntity saved=repo.save(newEntity);
        return ResponseEntity.ok(Map.of(
                "message", "Profile Bap added successfully",
                "data", saved
        ));

    }

    @Override
    public ResponseEntity<?> searchByOrderNo(String orderNo) {
        BapPRofileEntity byOrderNo=repo.findRowByOrderNo(orderNo);
        if (byOrderNo!=null){
            return ResponseEntity.ok(byOrderNo);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "byOrderNo NOT found"));
        }
    }

    @Override
    public ResponseEntity<?> updateByOrderNo(BapProfileRequest request) {
        BapPRofileEntity existing = repo.findRowByOrderNo(request.getOrderNo());

        if (existing==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Record with Fs not found"));
        }

                existing.setOrderNo(request.getOrderNo());
                existing.setSapNo(request.getSapNo());
                existing.setCpssNo(request.getCpssNo());
                existing.setDgCateg(request.getDgCateg());
                existing.setPriority(request.getPriority());
                existing.setProfile(request.getProfile());
                existing.setRequestType(request.getRequestType());
                existing.setDeveloper(request.getDeveloper());
                existing.setValidator(request.getValidator());
                existing.setValidationStatus(request.getValidationStatus());
                existing.setOrderReceive(request.getOrderReceive());
                existing.setSLAscheduledFinishDate(request.getSLAscheduledFinishDate());
                existing.setStartDate(request.getStartDate());
                existing.setFinishDate(request.getFinishDate());
                existing.setStatus(request.getStatus());
                existing.setRemarks(request.getRemarks());



        repo.save(existing);
        return ResponseEntity.ok(Map.of(
                "message", "Bap Profile Updated successfully",
                "data", existing
        ));
    }

    @Override
    public List<BapMonthlyCount> countByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Object[]> results = repo.countByDateRange(startDate, endDate);
        return results.stream()
                .map(obj -> new BapMonthlyCount((String) obj[0], ((Number) obj[1]).longValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BapMonthlyCount> countByMonthForYear(int year) {
        List<Object[]> result = repo.countByMonthForYearNative(year);

        // Convert Object[] to DgInventoryMonthlyCount DTO
        return result.stream().map(row -> {
            String monthName = ((String) row[0]).trim(); // sometimes padded with spaces
            Long count = ((Number) row[1]).longValue();
            return new BapMonthlyCount(monthName, count);
        }).toList();
    }
}
