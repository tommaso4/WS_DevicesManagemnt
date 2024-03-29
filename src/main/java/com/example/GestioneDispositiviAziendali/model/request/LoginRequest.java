package com.example.GestioneDispositiviAziendali.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Username request")
    private String username;
    @NotBlank(message = "Password request")
    private String password;
}
