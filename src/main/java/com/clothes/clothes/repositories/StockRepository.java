package com.clothes.clothes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clothes.clothes.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
    
}
