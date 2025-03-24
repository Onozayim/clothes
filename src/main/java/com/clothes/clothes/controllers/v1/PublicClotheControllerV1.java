package com.clothes.clothes.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.clothes.services.ClotheService;
import com.clothes.clothes.vars.JsonResponses;

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
}
