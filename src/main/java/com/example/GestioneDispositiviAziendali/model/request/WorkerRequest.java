package com.example.GestioneDispositiviAziendali.model.request;

import com.example.GestioneDispositiviAziendali.model.entities.Device;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WorkerRequest {
    @NotNull(message = "UserName is not specified")
    @NotEmpty(message = "UserName is empty")
    private String userName;
    @NotNull(message = "Name is not specified")
    @NotEmpty(message = "Name is empty")
    private String name;
    @NotNull(message = "Surname is not specified")
    @NotEmpty(message = "Surname is empty")
    private String surname;
    @Email(message = "Email is not valid")
    private String email;
    //    private String urlImg;
}
