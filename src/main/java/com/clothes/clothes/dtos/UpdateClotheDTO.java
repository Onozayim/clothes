package com.clothes.clothes.dtos;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateClotheDTO {
    @Size(min = 0, max = 25, message = "El título de la ropa tiene un límite de 25 caracteres")
    @NotBlank(message = "El título de la ropa es necesario")
    private String title;

    @Size(min = 0, max = 255, message = "La descripcion de la ropa tiene un límite de 255 caracteres")
    @NotBlank(message = "La descripcion de la ropa es necesario")
    private String description;

    private MultipartFile image;

    @NotNull(message = "El id de la ropa es necesario")
    private Long id;
}

