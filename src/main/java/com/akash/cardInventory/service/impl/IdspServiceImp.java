package com.akash.cardInventory.service.impl;

import com.akash.cardInventory.dto.IdspProfileRequest;
import com.akash.cardInventory.entity.IdspProfileEntity;
import com.akash.cardInventory.repository.IdspProfileRepo;
import com.akash.cardInventory.service.IdspSesvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IdspServiceImp implements IdspSesvice {

    private final IdspProfileRepo repo;

    @Autowired
    public IdspServiceImp(IdspProfileRepo repo) {
        this.repo = repo;
    }

    @Override
    public ResponseEntity<?> addProfile(IdspProfileRequest request) {
        List<IdspProfileEntity> fsNumber = repo.findAllRowByfs(request.getFs());

        if (!fsNumber.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Map.of("message", "Duplicate found: fs"));
        }

        IdspProfileEntity newEntity = IdspProfileEntity.builder()
                .profile(request.getProfile())
                .partnerCode(request.getPartnerCode())
                .version(request.getVersion())
                .fs(request.getFs())
                .edd(request.getEdd())
                .comment(request.getComment())
                .type(request.getType())
                .configurator(request.getConfigurator())
                .build();

        IdspProfileEntity saved = repo.save(newEntity);
        return ResponseEntity.ok(Map.of(
                "message", "Profile added successfully",
                "data", saved
        ));
    }

    @Override
    public ResponseEntity<?> searchByfs(String fs) {
       IdspProfileEntity byfs=repo.findRowByfs(fs);
       if (byfs!=null){
           return ResponseEntity.ok(byfs);
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body(Map.of("message", "fs NOT found"));
       }
    }

    @Override
    public ResponseEntity<?> searchByType(String type) {
        List<IdspProfileEntity> bytype=repo.findRowBytype(type);
        if (bytype!=null){
            return ResponseEntity.ok(bytype);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "type NOT found"));
        }
    }

    @Override
    public ResponseEntity<?> updateByfs(IdspProfileRequest request) {
        IdspProfileEntity existing = repo.findRowByfs(request.getFs());

        if (existing==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Record with Fs not found"));
        }

        existing.setProfile(request.getProfile());
        existing.setVersion(request.getVersion());
        existing.setFs(request.getFs());
        existing.setEdd(request.getEdd());
        existing.setComment(request.getComment());
        existing.setType(request.getType());
        existing.setConfigurator(request.getConfigurator());



        repo.save(existing);
        return ResponseEntity.ok(Map.of(
                "message", "Profile Updated successfully",
                "data", existing
        ));
    }
}
