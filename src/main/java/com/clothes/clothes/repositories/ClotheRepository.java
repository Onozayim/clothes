package com.clothes.clothes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clothes.clothes.entities.Clothe;

@Repository
public interface ClotheRepository extends JpaRepository<Clothe, Long>{

    
}