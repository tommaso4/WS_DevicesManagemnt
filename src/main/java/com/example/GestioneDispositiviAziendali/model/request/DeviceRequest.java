package com.example.GestioneDispositiviAziendali.model.request;

import com.example.GestioneDispositiviAziendali.enums.StatusDev;
import com.example.GestioneDispositiviAziendali.enums.TypeDev;
import com.example.GestioneDispositiviAziendali.model.entities.Worker;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class DeviceRequest {
    private TypeDev deviceType;
    private StatusDev status;
    private Integer idWorker;
}
