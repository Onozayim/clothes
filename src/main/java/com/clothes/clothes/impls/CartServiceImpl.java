package com.clothes.clothes.impls;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.clothes.dtos.CartDTO;
import com.clothes.clothes.dtos.StockDTO;
import com.clothes.clothes.dtos.UpdateCartDTO;
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

    public Cart addToCart(CartDTO cartDTO, User user) throws ConditionalException {
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

        return cart;
    }

    public Cart updateCart(UpdateCartDTO updateCartDTO, User user) throws ConditionalException {
        Cart cart = cartRepository.findById(updateCartDTO.getId())
                .orElseThrow(() -> new NoSuchElementException("Carrito no encontrado"));

        if(cart.getUser().getId() != user.getId())
            throw new ConditionalException("Usuario no valido");


        Stock stock = stockRepository.findById(cart.getStockId().getId())
                .orElseThrow(() -> new NoSuchElementException("Prenda no encontrada"));
        
        Short newStock = (short)(cart.getStock() + stock.getStock());

        if (updateCartDTO.getStock() > newStock)
            throw new ConditionalException("Stock insuficiente");


        cart.setStock(updateCartDTO.getStock());
        cart.setTotalPrice(cart.getPrice() * updateCartDTO.getStock());
        cartRepository.save(cart);

        stock.setStock((short)(newStock - updateCartDTO.getStock()));
        stockRepository.save(stock);

        return cart;
    }


    public void removeClotheFromCart(Long id, User user) throws ConditionalException {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Carrito no encontrado"));

        if(cart.getUser().getId() != user.getId())
            throw new ConditionalException("Usuario no valido");


        Stock stock = stockRepository.findById(cart.getStockId().getId())
                .orElseThrow(() -> new NoSuchElementException("Prenda no encontrada"));
        
        stock.setStock((short)(stock.getStock() + cart.getStock()));

        stockRepository.save(stock);
        cartRepository.deleteById(id);
    }
}
