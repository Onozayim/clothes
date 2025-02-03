package com.clothes.clothes.impls;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.clothes.dtos.StockDTO;
import com.clothes.clothes.entities.Stock;
import com.clothes.clothes.repositories.StockRepository;
import com.clothes.clothes.services.StockService;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository stockRepository;

    public void updateStock(StockDTO stockDTO) {
        Stock stock = stockRepository.findById(stockDTO.getId())
                .orElseThrow(() -> new NoSuchElementException("No se encontró el stock a actualizar"));

        stock.setStock((short) (stock.getStock() + stockDTO.getStock()));
        stockRepository.save(stock);
    }
}
