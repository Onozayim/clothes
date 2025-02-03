package com.clothes.clothes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clothes.clothes.entities.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    
}
