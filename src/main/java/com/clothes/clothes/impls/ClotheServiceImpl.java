package com.clothes.clothes.impls;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clothes.clothes.dtos.ClotheDTO;
import com.clothes.clothes.dtos.UpdateClotheDTO;
import com.clothes.clothes.entities.Clothe;
import com.clothes.clothes.repositories.ClotheRepository;
import com.clothes.clothes.responses.ClotheResponse;
import com.clothes.clothes.services.ClotheService;
import com.clothes.clothes.vars.StringConsts;

@Service
public class ClotheServiceImpl implements ClotheService {

    @Autowired
    ClotheRepository clotheRepository;

    public Clothe saveClothe(ClotheDTO clotheDto) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + clotheDto.getImage().getOriginalFilename();
        Path uploadPath = Path.of(StringConsts.ImagePath);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath))
            Files.createDirectories(uploadPath);

        Clothe clothe = new Clothe();
        clothe.setTitle(clotheDto.getTitle());
        clothe.setDescription(clotheDto.getDescription());
        clothe.setImage(filePath.toString());

        Files.copy(clotheDto.getImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        clotheRepository.save(clothe);

        return clothe;
    }

    public Clothe updateClothe(UpdateClotheDTO updateClotheDTO) throws IOException {
        Clothe clothe = clotheRepository.findById(updateClotheDTO.getId())
                .orElseThrow(() -> new NoSuchElementException("Prenda no encontrada"));

        clothe.setDescription(updateClotheDTO.getDescription());
        clothe.setTitle(updateClotheDTO.getTitle());

        if (updateClotheDTO.getImage() != null) {
            System.out.println("IMAGEN NO ES NULL");
            String uniqueFileName = UUID.randomUUID().toString() + "_"
                    + updateClotheDTO.getImage().getOriginalFilename();

            Path uploadPath = Path.of(StringConsts.ImagePath);
            Path filePath = uploadPath.resolve(uniqueFileName);
            Path oldFilePath = Path.of(clothe.getImage());

            if (!Files.exists(uploadPath))
                Files.createDirectories(uploadPath);

            Files.copy(updateClotheDTO.getImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            Files.deleteIfExists(oldFilePath);

            clothe.setImage(filePath.toString());
        } 
        
        clotheRepository.save(clothe);
        return clothe;
    }

    public Clothe findClotheOrThrow(Long id) {
        return clotheRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Prenda no encontrada"));
    }

    public Page<ClotheResponse> getPage(Pageable pageable) {
        return clotheRepository.findAllClothesWithoutStock(pageable);
    }

    public byte[] getImageFromId(String id) throws IOException {
        Clothe clothe = clotheRepository.findById(Long.valueOf(id)).orElseThrow();

        String filepath = clothe.getImage();

        System.out.println(filepath);

        byte[] image = Files.readAllBytes(new File(filepath).toPath());

        return image;
    }
}
