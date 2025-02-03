package com.clothes.clothes.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    @NotNull(message = "El stock es necesario")
    @Min(1)
    private Short stock;   

    @NotNull(message = "El id de la ropa a comprar es necesario")
    @Min(1)
    private Long stock_id;
}
