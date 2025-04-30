package com.clothes.clothes.controllers.v1;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.clothes.responses.ClotheRelationsResponse;
import com.clothes.clothes.services.ClotheService;
import com.clothes.clothes.vars.JsonResponses;

import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping(path = "v1/public/clothe")
public class PublicClotheControllerV1 {

    @Autowired
    JsonResponses jsonResponses;

    @Autowired
    ClotheService clotheService;

    @GetMapping(value = { "/", "" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClothes(@RequestParam(defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, 6, Sort.by("id").ascending());
        return jsonResponses.ReturnOkData(clotheService.getPage(pageable), "Prendas encontradas");
    }

    @GetMapping(value = { "/image/{id}/", "image/{id}" })
    public ResponseEntity<?> getClotheImage(@PathVariable("id") @NotEmpty(message = "Porfavor ingrese un id") String id)
            throws IOException {

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png"))
                .body(clotheService.getImageFromId(id));
    }

    @GetMapping(value = { "/", "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getClothe(
            @PathVariable("id") @NotEmpty(message = "Porfavor ingrese un id") String id) {

        return jsonResponses.ReturnOkData(new ClotheRelationsResponse(
                clotheService.findClotheOrThrow(Long.valueOf(id))), "Prenda encontrada");
    }
}
