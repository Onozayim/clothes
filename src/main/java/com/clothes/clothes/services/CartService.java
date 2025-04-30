package com.clothes.clothes.services;

import java.util.List;

import com.clothes.clothes.dtos.CartDTO;
import com.clothes.clothes.dtos.UpdateCartDTO;
import com.clothes.clothes.entities.Cart;
import com.clothes.clothes.entities.User;
import com.clothes.clothes.exceptions.ConditionalException;
import com.clothes.clothes.responses.CartResponse;

public interface CartService {
    public Cart addToCart(CartDTO cartDTO, User user) throws ConditionalException;
    public Cart updateCart(UpdateCartDTO updateCartDTO, User user) throws ConditionalException;
    public void removeClotheFromCart(Long id, User user) throws ConditionalException; 
    public List<CartResponse> getUserCart(User user); 
}
