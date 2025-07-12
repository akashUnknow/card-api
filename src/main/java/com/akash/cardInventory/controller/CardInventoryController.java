package com.akash.cardInventory.controller;

import com.akash.cardInventory.dto.CardInventoryRequest;
import com.akash.cardInventory.dto.CardInventoryResponse;
import com.akash.cardInventory.dto.PersoCardRequest;
import com.akash.cardInventory.entity.CardInventory;
import com.akash.cardInventory.service.CardInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "http://localhost:5173")
public class CardInventoryController {

    @Autowired
    private CardInventoryService inventoryService;

    @PostMapping("/add-card")
    public CardInventory createInventory(@RequestBody CardInventoryRequest request) {
        return inventoryService.createCardInventory(request);
    }

    @GetMapping("/search")
    public CardInventoryResponse searchByCardDescription(@RequestParam String description){
        return inventoryService.getCardInventoryByDescription(description);
    }

    @PostMapping("/perso-card")
    public ResponseEntity<CardInventory> handlePersoCard(@RequestBody PersoCardRequest request) {
        CardInventory updated = inventoryService.persoCardProcess(request);
        return ResponseEntity.ok(updated);
    }

}
