package com.clothes.clothes.responses;

import java.util.Date;

import com.clothes.clothes.entities.Clothe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CltoheResponse {

    public CltoheResponse(Clothe clothe) {
        this.description = clothe.getDescription();
        this.title = clothe.getTitle();
        this.image = clothe.getImage();
        this.created_at = clothe.getCreatedAt();
        this.updated_at = clothe.getUpdatedAt();
        this.price = clothe.getPrice();
        this.id = clothe.getId();
    }

    private String title;   
    private String description;
    private String image;
    private Date created_at;
    private Date updated_at;
    private float price;
    private Long id;
}
