package com.clothes.clothes.dtos;

import com.clothes.clothes.entities.Cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    public CartDTO(Cart cart) {
        this.stock = cart.getStock();
        this.stock_id = cart.getStockId().getId();
        this.id = cart.getId();
    }

    @NotNull(message = "El stock es necesario")
    @Min(1)
    private Short stock;   

    @NotNull(message = "El id de la ropa a comprar es necesario")
    @Min(1)
    private Long stock_id;

    private Long id;
}
