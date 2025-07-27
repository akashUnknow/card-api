package com.akash.cardInventory.controller;


import com.akash.cardInventory.dto.BapProfileRequest;
import com.akash.cardInventory.dto.IdspProfileRequest;
import com.akash.cardInventory.service.BapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bap")
@CrossOrigin(origins = "http://localhost:5173")
public class BapController {

    @Autowired
    private BapService bapProfileService;

    @PostMapping("/add-profile")
    public ResponseEntity<?> addProfile(@RequestBody BapProfileRequest request ){
        return bapProfileService.addProfile(request);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchByOrderNo(@RequestParam String orderNo ){
        return bapProfileService.searchByOrderNo(orderNo);
    }

    @PutMapping("/update-by-orderNo")
    public ResponseEntity<?> updateByOrderNo(@RequestBody BapProfileRequest request){

        return bapProfileService.updateByOrderNo(request);
    }
}
