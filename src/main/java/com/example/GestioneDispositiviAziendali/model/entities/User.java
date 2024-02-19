package com.example.GestioneDispositiviAziendali.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String username;
    private String name;
    private String surname;
    private String password;
}
