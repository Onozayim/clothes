package com.clothes.clothes.controllers.v1;

import org.springframework.web.bind.annotation.RestController;

import com.clothes.clothes.entities.Oc;
import com.clothes.clothes.responses.OcRelationsResponse;
import com.clothes.clothes.responses.OcResponse;
import com.clothes.clothes.services.AuthenticationService;
import com.clothes.clothes.services.OcService;
import com.clothes.clothes.utils.AuthUtils;
import com.clothes.clothes.vars.JsonResponses;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "v1/oc")
public class OcControllerV1 {
    @Autowired
    JsonResponses jsonResponses;

    @Autowired
    OcService oService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(value = { "", "/" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOc() {
        Oc oc = oService.saveOc(AuthUtils.getUserAuthenticated());
        return jsonResponses.ReturnOkData(new OcRelationsResponse(oc), "Oc creada");
    }

    @GetMapping(value = { "", "/" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOcWithDetails() {
        return jsonResponses.ReturnOkData(oService.getUserOcs(AuthUtils.getUserAuthenticated()), "Ocs");
    }

}
