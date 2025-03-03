package com.clothes.clothes.responses;

import java.util.Date;

import com.clothes.clothes.entities.Oc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OcResponse {

    public OcResponse (Oc oc) {
        this.setId(oc.getId());
        this.setPrice(oc.getPrice());
        this.setCreated_at(oc.getCreatedAt());
        this.setUpdated_at(oc.getUpdatedAt());
        // this.setUserResponse(new UserResponse(oc.getUser()));
    }

    private Long id;
    private float price;
    private Date created_at;
    private Date updated_at;
    // private UserResponse userResponse;
}
