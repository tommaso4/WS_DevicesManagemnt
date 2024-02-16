package com.example.GestioneDispositiviAziendali.controller;

import com.example.GestioneDispositiviAziendali.model.entities.Worker;
import com.example.GestioneDispositiviAziendali.service.WorkerSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkerCtrl {

    @Autowired
    private WorkerSvc workerSvc;

    @GetMapping("/worker")
    public ResponseEntity<CustomResponse> getAllWorkers(Pageable pageable) {
        Page<Worker> workers = workerSvc.getAllWorker(pageable);
        return CustomResponse.success(HttpStatus.OK.toString(),workers,HttpStatus.OK);
    }

}
