package com.example.GestioneDispositiviAziendali.exceptionHandler;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class ErrorMessage {
    String message;
    LocalDateTime date;
}
