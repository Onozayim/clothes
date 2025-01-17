package com.clothes.clothes.impls;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clothes.clothes.dtos.ClotheDTO;
import com.clothes.clothes.entities.Clothe;
import com.clothes.clothes.repositories.ClotheRepository;
import com.clothes.clothes.services.ClotheService;
import com.clothes.clothes.vars.StringConsts;

@Service
public class ClotheServiceImpl implements ClotheService{

    @Autowired
    ClotheRepository clotheRepository;

    public void SaveClothe(ClotheDTO clotheDto) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + clotheDto.getImage().getOriginalFilename();
        Path uploadPath = Path.of(StringConsts.ImagePath);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if(!Files.exists(uploadPath)) 
            Files.createDirectories(uploadPath);

        Clothe clothe = new Clothe();
        clothe.setTitle(clotheDto.getTitle());
        clothe.setDescription(clotheDto.getDescription());
        clothe.setImage(filePath.toString());

        Files.copy(clotheDto.getImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        clotheRepository.save(clothe);
    }
}
