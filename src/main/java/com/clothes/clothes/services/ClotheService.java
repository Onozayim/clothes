package com.clothes.clothes.services;

import java.io.IOException;

import com.clothes.clothes.dtos.ClotheDTO;

public interface ClotheService {
    public void SaveClothe(ClotheDTO clothe) throws IOException;
}
