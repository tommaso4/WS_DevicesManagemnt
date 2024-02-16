package com.example.GestioneDispositiviAziendali.repository;

import com.example.GestioneDispositiviAziendali.model.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DevRepo extends JpaRepository<Device,Integer>, PagingAndSortingRepository<Device,Integer> {
}
