package com.clothes.clothes.impls;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.clothes.dtos.CartDTO;
import com.clothes.clothes.dtos.StockDTO;
import com.clothes.clothes.entities.Cart;
import com.clothes.clothes.entities.Clothe;
import com.clothes.clothes.entities.Stock;
import com.clothes.clothes.entities.User;
import com.clothes.clothes.exceptions.ConditionalException;
import com.clothes.clothes.repositories.CartRepository;
import com.clothes.clothes.repositories.ClotheRepository;
import com.clothes.clothes.repositories.StockRepository;
import com.clothes.clothes.services.CartService;
import com.clothes.clothes.services.StockService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ClotheRepository clotheRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    StockService stockService;

    public void addToCart(CartDTO cartDTO, User user) throws ConditionalException {
        Stock stock = stockRepository.findById(cartDTO.getStock_id())
                .orElseThrow(() -> new NoSuchElementException("Prenda no encontrada"));

        if (cartDTO.getStock() > stock.getStock())
            throw new ConditionalException("Stock insuficiente");

        Clothe clothe = stock.getClothe();

        stockService.updateStock(new StockDTO((short) ((-1) * cartDTO.getStock()), cartDTO.getStock_id()));

        Cart cart = new Cart();

        cart.setStock(cartDTO.getStock());
        cart.setPrice(clothe.getPrice());
        cart.setTotalPrice(cartDTO.getStock() * clothe.getPrice());
        cart.setClothe(clothe);
        cart.setUser(user);
        cart.setStockId(stock);

        cartRepository.save(cart);
    }
}
