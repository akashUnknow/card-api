package com.akash.cardInventory.controller;

import com.akash.cardInventory.model.Card;
import com.akash.cardInventory.model.PersoCard;
import com.akash.cardInventory.repository.CardRepository;
import com.akash.cardInventory.repository.PersoCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "http://localhost:5173")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private PersoCardRepository persoCardRepository; // ✅ Add this

    @PostMapping("/add-card")
    public Card createOrUpdateCard(@RequestBody Card cardRequest) {
        Optional<Card> existingCard = cardRepository.findByDescriptionAndFormFactor(
                cardRequest.getDescription(),
                cardRequest.getFormFactor()
        );

        if (existingCard.isPresent()) {
            Card card = existingCard.get();
            card.setQuantity(card.getQuantity() + cardRequest.getQuantity()); // ✅ Add quantity
            return cardRepository.save(card); // ✅ Update existing
        } else {
            return cardRepository.save(cardRequest); // ✅ Save new
        }
    }


    @PostMapping("/cardperso")
    public PersoCard createPersoCard(@RequestBody PersoCard persoCard) {
        return persoCardRepository.save(persoCard);
    }

    @GetMapping("/count")
    public Map<String, Object> getCardQuantityByDescriptionAndFormFactor(
            @RequestParam String description,
            @RequestParam String formFactor) {

        long totalQuantity = cardRepository.getQuantityByDescriptionAndFormFactor(description, formFactor);

        Map<String, Object> response = new HashMap<>();
        response.put("description", description);
        response.put("formFactor", formFactor);
        response.put("count", totalQuantity);

        return response;
    }

    @PostMapping("/use")
    public ResponseEntity<?> useCard(@RequestBody Map<String, Object> payload) {
        String description = (String) payload.get("description");
        String formFactor = (String) payload.get("formFactor");
        int usedQuantity = (int) payload.get("usedQuantity");

        List<Card> cards = cardRepository.findAllByDescriptionAndFormFactor(description, formFactor);

        if (cards.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Card not found for given description and form factor");
        }

        // Total quantity from all matched rows
        int totalAvailable = cards.stream().mapToInt(Card::getQuantity).sum();

        if (totalAvailable < usedQuantity) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Insufficient card quantity. Available: " + totalAvailable);
        }

        int remainingToDeduct = usedQuantity;

        for (Card card : cards) {
            int q = card.getQuantity();
            if (q >= remainingToDeduct) {
                card.setQuantity(q - remainingToDeduct);
                cardRepository.save(card);
                break;
            } else {
                card.setQuantity(0);
                remainingToDeduct -= q;
                cardRepository.save(card);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Card used successfully");
        response.put("remainingTotalQuantity", cards.stream().mapToInt(Card::getQuantity).sum());

        return ResponseEntity.ok(response);
    }





}

