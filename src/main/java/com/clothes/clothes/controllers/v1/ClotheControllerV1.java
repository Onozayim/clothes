package com.clothes.clothes.controllers.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.clothes.dtos.ClotheDTO;
import com.clothes.clothes.dtos.StockDTO;
import com.clothes.clothes.dtos.UpdateClotheDTO;
import com.clothes.clothes.entities.Clothe;
import com.clothes.clothes.entities.Stock;
import com.clothes.clothes.repositories.ClotheRepository;
import com.clothes.clothes.repositories.StockRepository;
import com.clothes.clothes.responses.ClotheRelationsResponse;
import com.clothes.clothes.responses.CltoheResponse;
import com.clothes.clothes.responses.StockResponse;
import com.clothes.clothes.services.ClotheService;
import com.clothes.clothes.services.StockService;
import com.clothes.clothes.vars.JsonResponses;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "v1/clothe")
public class ClotheControllerV1 {
    @Autowired
    JsonResponses jsonResponses;

    @Autowired
    ClotheService clotheService;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ClotheRepository clotheRepository;

    @Autowired
    StockService stockService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveClothe(@Valid @ModelAttribute ClotheDTO clotheDTO) throws IOException {
        Clothe clothe = clotheService.saveClothe(clotheDTO);

        Stock stockS = new Stock();
        stockS.setSize("s");
        stockS.setClothe(clothe);
        stockRepository.save(stockS);

        Stock stockM = new Stock();
        stockM.setSize("m");
        stockM.setClothe(clothe);
        stockRepository.save(stockM);

        Stock stockB = new Stock();
        stockB.setSize("b");
        stockB.setClothe(clothe);
        stockRepository.save(stockB);

        List<StockResponse> stocks = new ArrayList<>();
        stocks.add(new StockResponse(stockS));
        stocks.add(new StockResponse(stockM));
        stocks.add(new StockResponse(stockB));

        return jsonResponses.ReturnOkData(new ClotheRelationsResponse(clothe, stocks), "Usuario creado");
    }

    @GetMapping(value = { "/", "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClothe(
            @PathVariable("id") @NotEmpty(message = "Porfavor ingrese un id") String id) {

        return jsonResponses.ReturnOkData(new ClotheRelationsResponse(
                clotheService.findClotheOrThrow(Long.valueOf(id))), "Prenda encontrada");
    }

    @GetMapping(value = { "/", "" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClothes(@RequestParam(defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, 6, Sort.by("id").ascending());
        return jsonResponses.ReturnOkData(clotheService.getPage(pageable), "Prendas encontradas");
    }

    @PutMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateClothe(@Valid @ModelAttribute UpdateClotheDTO clotheDTO) throws IOException {

        return jsonResponses.ReturnOkData(new CltoheResponse(clotheService.updateClothe(clotheDTO)),
                "Ropa actualizada");
    }

    @PutMapping(value = "update_stock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStock(@Valid @RequestBody StockDTO stockDTO) {
        stockService.updateStock(stockDTO);

        return jsonResponses.ReturnOkMessage("Stock actualizado");
    }

    @DeleteMapping(value = { "/", "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCltohe(
            @PathVariable("id") @NotEmpty(message = "Porfavor ingrese un id") String id) {

        clotheRepository.deleteById(Long.valueOf(id));

        return jsonResponses.ReturnOkMessage("Prenda eliminada");
    }

}
