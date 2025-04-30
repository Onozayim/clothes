package com.clothes.clothes.responses;

import com.clothes.clothes.entities.Stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockResponse {

    public StockResponse(Stock stock) {
        this.stock = stock.getStock();
        this.size = stock.getSize();
        this.id = stock.getId();
    }

    private Short stock;   

    private String size;

    private Long id;
}
