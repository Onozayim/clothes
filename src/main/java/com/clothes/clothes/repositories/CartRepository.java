package com.clothes.clothes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clothes.clothes.entities.Cart;
import com.clothes.clothes.entities.User;
import com.clothes.clothes.responses.CartResponse;

import jakarta.transaction.Transactional;



@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    @Query(value = "SELECT new com.clothes.clothes.responses.CartResponse(cart) From Cart cart where cart.user.id = ?1", nativeQuery = false)
    List<CartResponse> getUserCartResponse(Long user_id);

    @Query(value = "SELECT * From cart c WHERE c.user_id = ?1", nativeQuery = true)
    List<Cart> getUserCart(Long user_id);

    @Query(value = "SELECT SUM(c.total_price) FROM cart c where c.user_id = ?1", nativeQuery = true)
    float getTotalPrice(Long user_id);

    @Transactional
    long deleteByUser(User user);
}
