package com.clothes.clothes.responses;

import java.util.ArrayList;
import java.util.List;

import com.clothes.clothes.entities.Clothe;
import com.clothes.clothes.entities.Stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClotheRelationsResponse {

    public ClotheRelationsResponse(Clothe clothe, List<StockResponse> stocks) {
        this.title = clothe.getTitle();
        this.description = clothe.getDescription();
        this.stocks = stocks;
    }

    public ClotheRelationsResponse(Clothe clothe) {
        this.title = clothe.getTitle();
        this.description = clothe.getDescription();

        List<StockResponse> stocks = new ArrayList<>();
        
        for (Stock stockResponse : clothe.getStock()) {
            stocks.add(new StockResponse(stockResponse));
        }
        
        this.stocks = stocks;
    }

    private String title;   

    private String description;

    private List<StockResponse> stocks;
}
