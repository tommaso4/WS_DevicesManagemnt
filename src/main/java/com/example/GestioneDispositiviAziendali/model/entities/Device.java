package com.example.GestioneDispositiviAziendali.model.entities;

import com.example.GestioneDispositiviAziendali.enums.StatusDev;
import com.example.GestioneDispositiviAziendali.enums.TypeDev;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    private TypeDev deviceType;
    @Enumerated(EnumType.STRING)
    private StatusDev status;
    @ManyToOne
    @JoinColumn(name = "worker_id")
    @JsonIgnore
    private Worker worker;
}
