package com.example.GestioneDispositiviAziendali.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DevForWorkReq {
    @NotNull
    private Integer idWorker;
}
