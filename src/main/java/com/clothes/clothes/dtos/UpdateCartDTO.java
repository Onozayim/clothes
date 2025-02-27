package com.clothes.clothes.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartDTO {

    @NotNull(message = "El id de la ropa del carrito es necesario")
    @Min(1)
    private Long id;

    // @NotNull(message = "El id del stock es necesario")
    // private Long stock_id;

    @NotNull(message = "El stock es necesario")
    @Min(1)
    private Short stock;
}
