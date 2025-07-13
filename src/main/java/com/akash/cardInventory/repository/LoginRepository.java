package com.akash.cardInventory.repository;

import com.akash.cardInventory.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginRepository extends JpaRepository<Login,Long> {


    List<Login> findAllByuserName(String userName);
}
