package com.clothes.clothes.responses;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.clothes.clothes.entities.Oc;
import com.clothes.clothes.entities.OcDetalle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OcRelationsResponse {
    public OcRelationsResponse(Oc oc) {
        this.setId(oc.getId());
        this.setPrice(oc.getPrice());
        this.setCreated_at(oc.getCreatedAt());
        this.setUpdated_at(oc.getUpdatedAt());
        this.setUserResponse(new UserResponse(oc.getUser()));

        for (OcDetalle ocDetalle : oc.getOcDetalles()) {
            this.ocDetalleResponses.add(new OcDetalleResponse(ocDetalle));
        }
    }

    private Long id;
    private float price;
    private Date created_at;
    private Date updated_at;
    private UserResponse userResponse;
    public List<OcDetalleResponse> ocDetalleResponses = new ArrayList<>();
}
