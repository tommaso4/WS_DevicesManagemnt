package com.example.GestioneDispositiviAziendali.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String name;
    private String surname;
    private String email;
    private String urlImg;
    @OneToMany(mappedBy = "worker", fetch = FetchType.EAGER)
    List<Device> deviceList = new ArrayList<Device>();
}
