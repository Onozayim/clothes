package com.clothes.clothes.responses;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.clothes.clothes.entities.Oc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OcWithDetailsResponse {

    public OcWithDetailsResponse(Oc oc) {
        this.id = oc.getId();
        this.price = oc.getPrice();
        this.createdAt = oc.getCreatedAt();
        this.updatedAt = oc.getUpdatedAt();
        this.user = new UserResponse(oc.getUser());

        this.ocDetalles = oc.getOcDetalles().stream()
            .map(OcDetalleResponse::new)
            .collect(Collectors.toList());
    }

    private long id;
    private float price;
    private Date createdAt;
    private Date updatedAt;
    private UserResponse user;
    private List<OcDetalleResponse> ocDetalles = new ArrayList<>();
}
