package com.clothes.clothes.controllers.v1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.clothes.dtos.RegisterUserDto;
import com.clothes.clothes.dtos.AuthDTO;
import com.clothes.clothes.entities.User;
import com.clothes.clothes.responses.LoginResponse;
import com.clothes.clothes.services.AuthenticationService;
import com.clothes.clothes.services.JwtService;
import com.clothes.clothes.vars.JsonResponses;

@RequestMapping("v1/auth")
@RestController
public class AuthControllerV1 {
    @Autowired
    JwtService jwtService;
    
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    JsonResponses jsonResponses;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody AuthDTO AuthDTO) {
        User authenticatedUser = authenticationService.authenticate(AuthDTO);

        Map<String, Object> extraClaims = new HashMap<>();

        extraClaims.put("email", authenticatedUser.getEmail());

        return jsonResponses.ReturnOkData(
            new LoginResponse(jwtService.generateToken(extraClaims, authenticatedUser), jwtService.getExpirationTime()),
            "Ususario logeado"
        );
    }
}
