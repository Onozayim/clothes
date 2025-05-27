package com.clothes.clothes.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.clothes.annotations.ValidId;
import com.clothes.clothes.dtos.UpdateUserDTO;
import com.clothes.clothes.responses.UserResponse;
import com.clothes.clothes.services.AuthenticationService;
import com.clothes.clothes.services.JwtService;
import com.clothes.clothes.services.UserService;
import com.clothes.clothes.utils.AuthUtils;
import com.clothes.clothes.vars.JsonResponses;
import com.clothes.clothes.vars.StringConsts;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "v1/user")
public class UserControllerV1 {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    JwtService jwtService;
    @Autowired
    JsonResponses jsonResponses;

    // @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/me")
    public ResponseEntity<?> getME() {
        return jsonResponses.ReturnOkData(new UserResponse(AuthUtils.getUserAuthenticated()), StringConsts.Done);
    }

    // @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = { "/delete/", "/delete/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(
            @PathVariable("id") @ValidId String id) {

        userService.findUserByIdOrThrow(Long.valueOf(id));

        userService.deleteUserById(Long.valueOf(id));
        return jsonResponses.ReturnOkMessage("Usuario eliminado");
    }

    @PutMapping(value = { "/", "" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserDTO updateUserDto) {

        userService.updateUser(updateUserDto, AuthUtils.getUserAuthenticated());

        return jsonResponses.ReturnOkMessage("Usuario Actualizado");
    }
}