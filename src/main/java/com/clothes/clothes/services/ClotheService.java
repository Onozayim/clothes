package com.clothes.clothes.services;

import java.io.IOException;

import com.clothes.clothes.dtos.ClotheDTO;
import com.clothes.clothes.dtos.UpdateClotheDTO;
import com.clothes.clothes.entities.Clothe;

public interface ClotheService {
    public Clothe saveClothe(ClotheDTO clothe) throws IOException;
    public Clothe updateClothe(UpdateClotheDTO clothe) throws IOException;
    public Clothe findClotheOrThrow(Long id);
}
