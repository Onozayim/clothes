package com.clothes.clothes.responses;

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
    }

    private String title;   
    private String description;
}
