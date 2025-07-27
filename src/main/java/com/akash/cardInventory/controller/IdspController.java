package com.akash.cardInventory.controller;

import com.akash.cardInventory.dto.IdspProfileRequest;
import com.akash.cardInventory.entity.IdspProfileEntity;
import com.akash.cardInventory.service.DgInventoryService;
import com.akash.cardInventory.service.IdspSesvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("api/idsp")
public class IdspController {

    @Autowired
    private IdspSesvice idspSesvice;
    @PostMapping("/add")
    public ResponseEntity<?> addProfile(@RequestBody IdspProfileRequest request){
        return idspSesvice.addProfile(request);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByfs(@RequestParam String fs ){
        return idspSesvice.searchByfs(fs);
    }

    @GetMapping("/type")
    public ResponseEntity<?> searchByType(@RequestParam String type ){
        return idspSesvice.searchByType(type);
    }

    @PutMapping("/update-by-fs")
    public ResponseEntity<?> updateByfs(@RequestBody IdspProfileRequest request){

        return idspSesvice.updateByfs(request);
    }
}
