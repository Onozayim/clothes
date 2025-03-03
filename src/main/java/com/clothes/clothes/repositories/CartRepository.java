package com.clothes.clothes.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clothes.clothes.entities.Cart;
import com.clothes.clothes.entities.User;

import jakarta.transaction.Transactional;



@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    @Query(value = "SELECT * From cart c WHERE c.user_id = ?1", nativeQuery = true)
    Collection<Cart> getUserCart(Long user_id);

    @Query(value = "SELECT SUM(c.total_price) FROM cart c where c.user_id = ?1", nativeQuery = true)
    float getTotalPrice(Long user_id);

    @Transactional
    long deleteByUser(User user);
}
