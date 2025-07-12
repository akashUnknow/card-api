package com.akash.cardInventory.service.impl;
import com.akash.cardInventory.dto.CardInventoryRequest;
import com.akash.cardInventory.dto.CardInventoryResponse;
import com.akash.cardInventory.dto.PersoCardRequest;
import com.akash.cardInventory.entity.CardInventory;
import com.akash.cardInventory.entity.PersoCardLog;
import com.akash.cardInventory.repository.CardInventoryRepository;
import com.akash.cardInventory.repository.PersoCardLogRepository;
import com.akash.cardInventory.service.CardInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardInventoryServiceImpl implements CardInventoryService {



    @Autowired
    private PersoCardLogRepository persoCardLogRepo;

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

    @Override
    public CardInventoryResponse getCardInventoryByDescription(String cardDescription) {
        List<CardInventory> records = repository.findAllByCardDescription(cardDescription);
        if (records.isEmpty()) {
            return null; // or throw custom NotFoundException
        }
        int tri = records.stream().mapToInt(CardInventory::getTri).sum();
        int twoFF = records.stream().mapToInt(CardInventory::getTwoFF).sum();
        int threeFF = records.stream().mapToInt(CardInventory::getThreeFF).sum();
        int fourFF = records.stream().mapToInt(CardInventory::getFourFF).sum();
        int mff2 = records.stream().mapToInt(CardInventory::getMff2).sum();
        int b4 = records.stream().mapToInt(CardInventory::getB4).sum();
        int total = tri + twoFF + threeFF + fourFF + mff2 + b4;
        return CardInventoryResponse.builder()
                .cardDescription(cardDescription)
                .tri(tri)
                .twoFF(twoFF)
                .threeFF(threeFF)
                .fourFF(fourFF)
                .mff2(mff2)
                .b4(b4)
                .totalQuantity(total)
                .build();


    }


    @Override
    public CardInventory persoCardProcess(PersoCardRequest request) {
        List<CardInventory> list = repository.findAllByCardDescription(request.getCardDescription());

        if (list.isEmpty()) {
            throw new RuntimeException("Card not found for: " + request.getCardDescription());
        }

        CardInventory card = list.get(0);

        int deduction = request.getRst() + request.getTelcaPersoTest() + request.getShredCard();

        int currentFormFactorQuantity;

        switch (request.getFormFactor().toUpperCase()) {
            case "TRI" -> currentFormFactorQuantity = card.getTri();
            case "2FF" -> currentFormFactorQuantity = card.getTwoFF();
            case "3FF" -> currentFormFactorQuantity = card.getThreeFF();
            case "4FF" -> currentFormFactorQuantity = card.getFourFF();
            case "MFF2" -> currentFormFactorQuantity = card.getMff2();
            case "B4" -> currentFormFactorQuantity = card.getB4();
            default -> throw new IllegalArgumentException("Invalid form factor: " + request.getFormFactor());
        }

        if (deduction > currentFormFactorQuantity) {
            throw new RuntimeException("Not enough cards available in form factor " + request.getFormFactor() +
                    ". Available: " + currentFormFactorQuantity + ", Required: " + deduction);
        }

        // Perform deduction safely
        switch (request.getFormFactor().toUpperCase()) {
            case "TRI" -> card.setTri(currentFormFactorQuantity - deduction);
            case "2FF" -> card.setTwoFF(currentFormFactorQuantity - deduction);
            case "3FF" -> card.setThreeFF(currentFormFactorQuantity - deduction);
            case "4FF" -> card.setFourFF(currentFormFactorQuantity - deduction);
            case "MFF2" -> card.setMff2(currentFormFactorQuantity - deduction);
            case "B4" -> card.setB4(currentFormFactorQuantity - deduction);
        }

        card.setTotalQuantity(card.getTotalQuantity() - deduction);
        CardInventory updated = repository.save(card);

        // Save log
        PersoCardLog log = PersoCardLog.builder()
                .cardDescription(request.getCardDescription())
                .formFactor(request.getFormFactor())
                .usedQuantity(request.getUsedQuantity())
                .profile(request.getProfile())
                .configurator(request.getConfigurator())
                .issuedTo(request.getIssuedTo())
                .customer(request.getCustomer())
                .rst(request.getRst())
                .telcaPersoTest(request.getTelcaPersoTest())
                .shredCard(request.getShredCard())
                .timestamp(LocalDateTime.now())
                .build();

        persoCardLogRepo.save(log);

        return updated;
    }


}
