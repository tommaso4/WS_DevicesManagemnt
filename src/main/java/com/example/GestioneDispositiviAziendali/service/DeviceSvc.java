package com.example.GestioneDispositiviAziendali.service;

import com.example.GestioneDispositiviAziendali.exceptionHandler.NotFoundException;
import com.example.GestioneDispositiviAziendali.model.entities.Device;
import com.example.GestioneDispositiviAziendali.model.entities.Worker;
import com.example.GestioneDispositiviAziendali.model.request.DevForWorkReq;
import com.example.GestioneDispositiviAziendali.model.request.DeviceRequest;
import com.example.GestioneDispositiviAziendali.repository.DevRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeviceSvc {
    @Autowired
    private DevRepo devRepo;
    @Autowired
    private WorkerSvc workerSvc;

    public Page<Device> getAllDevices(Pageable pageable) {
        return devRepo.findAll(pageable);
    }

    public Device getDevById(int id) throws NotFoundException {
        return devRepo.findById(id).orElseThrow(() -> new NotFoundException("Device with id: " + id + " not found"));
    }

    public Device createDevice(DeviceRequest deviceRequest) throws NotFoundException {
        Device device = new Device();
        device.setDeviceType(deviceRequest.getDeviceType());
        device.setStatus(deviceRequest.getStatus());
        return devRepo.save(device);
    }

    public Device upDateWorkerToDevice(int id,DevForWorkReq dev) throws NotFoundException {
        Device device = getDevById(id);
        Worker worker = workerSvc.getWoerkerById(dev.getIdWorker());
        device.setWorker(worker);
        return devRepo.save(device);
    }

    public Device upDateDevice(int id,DeviceRequest deviceRequest) throws NotFoundException {
        Device device = getDevById(id);
        Worker worker = workerSvc.getWoerkerById(deviceRequest.getIdWorker());
        device.setDeviceType(deviceRequest.getDeviceType());
        device.setStatus(deviceRequest.getStatus());
        device.setWorker(worker);
        return devRepo.save(device);
    }

    public void deleteDevice (int id) throws NotFoundException {
        Device device = getDevById(id);
        devRepo.delete(device);
    }
}
