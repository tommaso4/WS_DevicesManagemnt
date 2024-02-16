package com.example.GestioneDispositiviAziendali.controller;

import com.example.GestioneDispositiviAziendali.exceptionHandler.NotFoundException;
import com.example.GestioneDispositiviAziendali.model.entities.Worker;
import com.example.GestioneDispositiviAziendali.model.request.WorkerRequest;
import com.example.GestioneDispositiviAziendali.service.WorkerSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkerCtrl {

    @Autowired
    private WorkerSvc workerSvc;

    @GetMapping("/worker")
    public ResponseEntity<CustomResponse> getAllWorkers(Pageable pageable) {
        Page<Worker> workers = workerSvc.getAllWorker(pageable);
        return CustomResponse.success(HttpStatus.OK.toString(), workers, HttpStatus.OK);
    }

    @GetMapping("/worker/{id}")
    public ResponseEntity<CustomResponse> getWorkerById(@PathVariable int id) throws NotFoundException {
        Worker worker = workerSvc.getWoerkerById(id);
        return CustomResponse.success(HttpStatus.OK.toString(), worker, HttpStatus.OK);
    }

    @PostMapping("/worker")
    public ResponseEntity<CustomResponse> saveWorker(@RequestBody @Validated WorkerRequest workerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage()).toList().toString();
            return CustomResponse.failure(errorMessage, HttpStatus.BAD_REQUEST);
        }
        Worker worker = workerSvc.saveWorker(workerRequest);
        return CustomResponse.success(HttpStatus.CREATED.toString(), worker, HttpStatus.CREATED);
    }

    @PutMapping("/worker/{id}")
    public ResponseEntity<CustomResponse> upLoadWorker(@PathVariable int id,
                                                       @RequestBody @Validated WorkerRequest workerRequest,
                                                       BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage()).toList().toString();
            return CustomResponse.failure(errorMessage, HttpStatus.BAD_REQUEST);
        }
        Worker worker = workerSvc.upDateWorker(id, workerRequest);
        return CustomResponse.success(HttpStatus.OK.toString(), worker, HttpStatus.OK);
    }

    @DeleteMapping("/worker/{id}")
    public ResponseEntity<CustomResponse> deleteWorker(@PathVariable int id) throws NotFoundException {
        workerSvc.deleteWorker(id);
        return CustomResponse.emptyResponse("Worker with id: " + id + " deleted", HttpStatus.OK);
    }
}
