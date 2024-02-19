package com.example.GestioneDispositiviAziendali.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserReq {

    @NotBlank(message = "username required")
    private String username;
    @NotBlank(message = "name required")
    private String name;
    @NotBlank(message = "surname required")
    private String surname;
    @NotBlank(message = "password required")
    private String password;
}
