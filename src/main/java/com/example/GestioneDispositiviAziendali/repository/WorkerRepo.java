package com.example.GestioneDispositiviAziendali.repository;

import com.example.GestioneDispositiviAziendali.model.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WorkerRepo extends JpaRepository<Worker,Integer>, PagingAndSortingRepository<Worker,Integer> {
}
