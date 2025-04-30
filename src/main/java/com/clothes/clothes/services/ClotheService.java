package com.clothes.clothes.services;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clothes.clothes.dtos.ClotheDTO;
import com.clothes.clothes.dtos.UpdateClotheDTO;
import com.clothes.clothes.entities.Clothe;
import com.clothes.clothes.responses.ClotheResponse;

public interface ClotheService {
    public Clothe saveClothe(ClotheDTO clothe) throws IOException;
    public Clothe updateClothe(UpdateClotheDTO clothe) throws IOException;
    public Clothe findClotheOrThrow(Long id);
    public Page<ClotheResponse> getPage(Pageable pageable);
    public byte[] getImageFromId(String id) throws IOException;
}
