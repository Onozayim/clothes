package com.clothes.clothes.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    @NotNull(message = "El stock es necesario")
    private Short stock;

    @NotNull(message = "El id del stock es necesario")
    private Long id;
}
