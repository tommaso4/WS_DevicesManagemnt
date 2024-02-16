package com.example.GestioneDispositiviAziendali.model.request;

import com.example.GestioneDispositiviAziendali.enums.StatusDev;
import com.example.GestioneDispositiviAziendali.enums.TypeDev;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeviceRequest {
    @NotNull(message = "deviceType is not specified")
    private TypeDev deviceType;
    @NotNull(message = "stutus is not specified")
    private StatusDev status;
    @NotNull(message = "idWorker is not specified")
    private Integer idWorker;
}
