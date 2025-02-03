package com.clothes.clothes.services;

import com.clothes.clothes.dtos.CartDTO;
import com.clothes.clothes.entities.User;
import com.clothes.clothes.exceptions.ConditionalException;

public interface CartService {
    public void addToCart(CartDTO cartDTO, User user) throws ConditionalException;
}
