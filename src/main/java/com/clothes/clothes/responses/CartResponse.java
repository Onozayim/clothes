package com.clothes.clothes.responses;

import java.util.Date;

import com.clothes.clothes.entities.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.stock = cart.getStock();
        this.price = cart.getPrice();
        this.totalPrice = cart.getTotalPrice();
        this.createdAt = cart.getCreatedAt();
        this.updatedAt = cart.getUpdatedAt();
        this.clothe = new ClotheResponse(cart.getClothe());
        this.stockId = new StockResponse(cart.getStockId());
        this.user = new UserResponse(cart.getUser());
    }

    private Long id;

    private Short stock;

    private float price;

    private float totalPrice;

    private Date createdAt;

    private Date updatedAt;

    private ClotheResponse clothe;

    private StockResponse stockId;

    private UserResponse user;
}
