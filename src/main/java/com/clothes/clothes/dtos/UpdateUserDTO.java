package com.clothes.clothes.dtos;

import com.clothes.clothes.annotations.PhoneNumber;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserDTO {
    @Size(min = 0, max = 50, message = "El nombre de usuario tiene un límite de 50 caracteres")
    @NotBlank(message = "El nombre de usuario es necesario")
    private String fullName;

    @Size(min = 0, max = 100, message = "El correo tiene un límite de 100 caracteres")
    @NotBlank(message = "El correo es necesario")
    @Email(message = "El correo es invalido")
    private String email;

    @Size(min = 0, max = 15, message = "El número teléfonico no puede tener más de 10 digitos")
    @NotBlank(message = "El número telefonico es necesario")
    @PhoneNumber
    private String phoneNumber;
}
