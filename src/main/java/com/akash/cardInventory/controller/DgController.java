package com.akash.cardInventory.controller;


import com.akash.cardInventory.dto.DgInventoryMonthlyCount;
import com.akash.cardInventory.dto.DgInventoryRequest;

import com.akash.cardInventory.dto.LoginActivityRequest;
import com.akash.cardInventory.entity.DgInventory;
import com.akash.cardInventory.entity.Login;
import com.akash.cardInventory.service.DgInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("api/dg")
public class DgController {
    @Autowired
    private DgInventoryService inventoryService;



    @PostMapping("/add-card")
    public ResponseEntity<?> createInventory(@RequestBody DgInventoryRequest request) {
        return inventoryService.createDgInventory(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginActivityRequest request) {
        List<Login> users = inventoryService.LoginActivity(request);

        if (users.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }

        Login user = users.get(0);
        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }

        // Simulate login token (use JWT in real apps)
        String dummyToken = "sample-token";

        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "token", dummyToken,
                "user",Map.of( "id", user.getId(),
                        "name", user.getUserName(),
                        "username", user.getId() )
        ));
    }

    @GetMapping("/search")
    public ResponseEntity<?> serachGdIndia(@RequestParam String GdIndia) {
        return inventoryService.serachDgGdIndia(GdIndia);
    }

    @PutMapping("/update-by-gdindia")
    public ResponseEntity<?> updateByGdIndia(@RequestBody DgInventoryRequest request) {
        return inventoryService.updateByGdIndia(request);
    }

    @GetMapping("/dg-inventory/monthly-count/{year}")
    public ResponseEntity<List<DgInventoryMonthlyCount>> getMonthlyCount(@PathVariable int year) {
        return ResponseEntity.ok(inventoryService.countByMonthForYear(year));
    }

    @GetMapping("/dg-inventory/monthly-count")
    public ResponseEntity<List<DgInventoryMonthlyCount>> getMonthlyCountByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return ResponseEntity.ok(inventoryService.countByDateRange(startDate, endDate));
    }



}
