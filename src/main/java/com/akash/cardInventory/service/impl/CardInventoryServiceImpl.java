package com.akash.cardInventory.service.impl;
import com.akash.cardInventory.dto.CardInventoryRequest;
import com.akash.cardInventory.entity.CardInventory;
import com.akash.cardInventory.repository.CardInventoryRepository;
import com.akash.cardInventory.service.CardInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CardInventoryServiceImpl implements CardInventoryService {

    @Autowired
    private CardInventoryRepository repository;


    @Override
    public CardInventory createCardInventory(CardInventoryRequest request) {

        List<CardInventory> existingList = repository.findAllByCardDescription(request.getCardDescription());

        if (!existingList.isEmpty()) {
            // Pick the first record to update
            CardInventory first = existingList.get(0);

            // Sum existing and incoming values for each form factor
            int updatedTri = existingList.stream().mapToInt(CardInventory::getTri).sum() + request.getTri();
            int updatedTwoFF = existingList.stream().mapToInt(CardInventory::getTwoFF).sum() + request.getTwoFF();
            int updatedThreeFF = existingList.stream().mapToInt(CardInventory::getThreeFF).sum() + request.getThreeFF();
            int updatedFourFF = existingList.stream().mapToInt(CardInventory::getFourFF).sum() + request.getFourFF();
            int updatedMFF2 = existingList.stream().mapToInt(CardInventory::getMff2).sum() + request.getMff2();
            int updatedB4 = existingList.stream().mapToInt(CardInventory::getB4).sum() + request.getB4();

            int totalQuantity = updatedTri + updatedTwoFF + updatedThreeFF + updatedFourFF + updatedMFF2 + updatedB4;

            // ✅ Update all fields
            first.setTri(updatedTri);
            first.setTwoFF(updatedTwoFF);
            first.setThreeFF(updatedThreeFF);
            first.setFourFF(updatedFourFF);
            first.setMff2(updatedMFF2);
            first.setB4(updatedB4);
            first.setTotalQuantity(totalQuantity);

            // Delete duplicate rows (except first)
            existingList.stream().skip(1).forEach(repository::delete);

            return repository.save(first);
        } else {
            // No existing records — create a new one
            int total = request.getTri() + request.getTwoFF() + request.getThreeFF()
                    + request.getFourFF() + request.getMff2() + request.getB4();

            CardInventory newCard = CardInventory.builder()
                    .cardDescription(request.getCardDescription())
                    .tri(request.getTri())
                    .twoFF(request.getTwoFF())
                    .threeFF(request.getThreeFF())
                    .fourFF(request.getFourFF())
                    .mff2(request.getMff2())
                    .b4(request.getB4())
                    .totalQuantity(total)
                    .build();

            return repository.save(newCard);
        }
    }

}
