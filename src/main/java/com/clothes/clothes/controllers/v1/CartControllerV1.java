package com.clothes.clothes.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clothes.clothes.dtos.CartDTO;
import com.clothes.clothes.dtos.UpdateCartDTO;
import com.clothes.clothes.entities.Cart;
import com.clothes.clothes.entities.User;
import com.clothes.clothes.exceptions.ConditionalException;
import com.clothes.clothes.repositories.CartRepository;
import com.clothes.clothes.services.AuthenticationService;
import com.clothes.clothes.services.CartService;
import com.clothes.clothes.utils.AuthUtils;
import com.clothes.clothes.vars.JsonResponses;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "v1/cart")
public class CartControllerV1 {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    JsonResponses jsonResponses;

    @Autowired
    CartService cartService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(value = { "/",
            "" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addToCart(@Valid @RequestBody CartDTO cartDTO) throws ConditionalException {

        Cart cart = cartService.addToCart(cartDTO, AuthUtils.getUserAuthenticated());

        return jsonResponses.ReturnOkData(new CartDTO(cart), "Added to Cart");
    }

    @GetMapping(value = { "/",
            "" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCartInfo() throws ConditionalException {

        User user = AuthUtils.getUserAuthenticated();

        return jsonResponses.ReturnOkData(cartService.getUserCart(user), "Added to Cart");
    }

    @DeleteMapping(value = { "/", "/{id}", "" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCltohe(
            @PathVariable("id") @NotEmpty(message = "Porfavor ingrese un id") String id) throws ConditionalException {

        cartService.removeClotheFromCart(Long.valueOf(id), AuthUtils.getUserAuthenticated());

        return jsonResponses.ReturnOkMessage("Prenda eliminada");
    }

    @PutMapping(value = { "/",
            "" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateClothe(@Valid @RequestBody UpdateCartDTO updateCart) throws ConditionalException {

        Cart cart = cartService.updateCart(updateCart, AuthUtils.getUserAuthenticated());
        return jsonResponses.ReturnOkData(new CartDTO(cart), null);
    }
}
