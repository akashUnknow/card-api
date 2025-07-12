package com.akash.cardInventory.service;

import com.akash.cardInventory.dto.CardInventoryRequest;
import com.akash.cardInventory.dto.CardInventoryResponse;
import com.akash.cardInventory.dto.PersoCardRequest;
import com.akash.cardInventory.entity.CardInventory;

public interface CardInventoryService {
    CardInventory createCardInventory(CardInventoryRequest request);

    CardInventoryResponse getCardInventoryByDescription(String description);

    CardInventory persoCardProcess(PersoCardRequest request);

}
