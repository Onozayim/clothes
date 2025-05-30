package com.clothes.clothes.impls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.clothes.clothes.email.EmailDetails;
import com.clothes.clothes.email.EmailService;
import com.clothes.clothes.entities.Cart;
import com.clothes.clothes.entities.Oc;
import com.clothes.clothes.entities.OcDetalle;
import com.clothes.clothes.entities.User;
import com.clothes.clothes.repositories.CartRepository;
import com.clothes.clothes.repositories.OcDetalleRepository;
import com.clothes.clothes.repositories.OcRepository;
import com.clothes.clothes.responses.OcWithDetailsResponse;
import com.clothes.clothes.services.OcService;

@Service
public class OcServiceImpl implements OcService{
    @Autowired
    CartRepository cartRepository;

    @Autowired
    OcRepository ocRepository;

    @Autowired
    OcDetalleRepository ocDetalleRepository;

    @Autowired
    EmailService emailService;

    public Oc saveOc(User user) {

        Oc oc = new Oc();
        oc.setPrice(cartRepository.getTotalPrice(user.getId()));
        oc.setUser(user);

        ocRepository.save(oc);

        Collection<Cart> user_cart = cartRepository.getUserCart(user.getId());

        List<OcDetalle> details = new ArrayList<>();

        for (Cart items : user_cart) {
            OcDetalle ocDetalle = new OcDetalle();
            ocDetalle.setPrice(items.getPrice());
            ocDetalle.setTotalPrice(items.getTotalPrice());
            ocDetalle.setStock(items.getStock());
            ocDetalle.setStockId(items.getStockId());
            ocDetalle.setUser(items.getUser());
            ocDetalle.setClothe(items.getClothe());
            ocDetalle.setOc(oc);

            ocDetalleRepository.save(ocDetalle);

            details.add(ocDetalle);
        }

        // oc.setOcDetalles(details);

        cartRepository.deleteByUser(user);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(user.getEmail());
        emailDetails.setSubject("Orden de compra realizada!");

        Context context = new Context();
        context.setVariable("name", user.getFullName());
        context.setVariable("precio", oc.getPrice());

        emailService.sendHTMLEmail(emailDetails, context, "OcEmail");

        return oc;
    }
    public List<OcWithDetailsResponse> getUserOcs(User user) {
        return ocRepository.getUserOcs(user.getId());
    }
}
