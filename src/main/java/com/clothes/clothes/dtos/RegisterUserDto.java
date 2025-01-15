package com.clothes.clothes.dtos;

import com.clothes.clothes.annotations.PasswordMatching;
import com.clothes.clothes.annotations.StrongPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@PasswordMatching(
    password = "password",
    confirmPassword = "confirmPassword",
    message = "Las contraseñas deben coincidir"
)
public class RegisterUserDto {
    @Size(min = 0, max = 50, message = "El nombre de usuario tiene un límite de 50 caracteres")
    @NotBlank(message = "El nombre de usuario es necesario")
    private String fullName;

    @Size(min = 0, max = 100, message = "El correo tiene un límite de 100 caracteres")
    @NotBlank(message = "El correo es necesario")
    @Email(message = "El correo es invalido")
    private String email;

    @Size(min = 0, max = 75, message = "La contraseña tiene un límite de 75 caracteres")
    @NotBlank(message = "La contraseña es necesaria")
    @StrongPassword
    private String password;

    @Size(min = 0, max = 75, message = "La contraseña tiene un límite de 75 caracteres")
    @NotBlank(message = "Favor de confirmar la contraseña")
    private String confirmPassword;
}
