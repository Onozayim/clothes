package com.clothes.clothes.controllers.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.clothes.dtos.ClotheDTO;
import com.clothes.clothes.services.ClotheService;
import com.clothes.clothes.vars.JsonResponses;

import jakarta.validation.Valid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(path = "v1/clothe")
public class ClotheControllerV1 {
    @Autowired
    JsonResponses jsonResponses;

    @Autowired
    ClotheService clotheService;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveClothe(@Valid @ModelAttribute ClotheDTO clotheDTO) throws IOException {
        clotheService.SaveClothe(clotheDTO);

        return jsonResponses.ReturnOkData(clotheDTO, "Usuario creado");
    }

}
