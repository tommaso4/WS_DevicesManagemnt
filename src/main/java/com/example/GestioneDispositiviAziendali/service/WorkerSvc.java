package com.example.GestioneDispositiviAziendali.service;

import com.example.GestioneDispositiviAziendali.exceptionHandler.NotFoundException;
import com.example.GestioneDispositiviAziendali.model.entities.Worker;
import com.example.GestioneDispositiviAziendali.model.request.WorkerRequest;
import com.example.GestioneDispositiviAziendali.repository.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class WorkerSvc {
    @Autowired
    private WorkerRepo workerRepo;

    public Page<Worker> getAllWorker(Pageable pageable) {
        return workerRepo.findAll(pageable);
    }

    public Worker getWoerkerById(int id) throws NotFoundException {
        return workerRepo.findById(id).orElseThrow(() ->
                new NotFoundException("Worker with id:" + id + " not found"));
    }

    public Worker saveWorker(WorkerRequest workerRequest){
        Worker worker = new Worker();

        worker.setUserName(workerRequest.getUserName());
        worker.setName(workerRequest.getName());
        worker.setSurname(workerRequest.getSurname());
        worker.setEmail(workerRequest.getEmail());
        return workerRepo.save(worker);
    }
    public Worker upDateWorker(int id,WorkerRequest workerRequest) throws NotFoundException {
        Worker worker = getWoerkerById(id);

        worker.setUserName(workerRequest.getUserName());
        worker.setName(workerRequest.getName());
        worker.setSurname(workerRequest.getSurname());
        worker.setEmail(workerRequest.getEmail());
        return workerRepo.save(worker);
    }

    public Worker uploadImgWorker (int id, String urlImg) throws NotFoundException {
        Worker worker = getWoerkerById(id);
        worker.setUrlImg(urlImg);
        return workerRepo.save(worker);
    }

    public void deleteWorker (int id) throws NotFoundException {
        Worker worker = getWoerkerById(id);
        workerRepo.delete(worker);
    }
}
