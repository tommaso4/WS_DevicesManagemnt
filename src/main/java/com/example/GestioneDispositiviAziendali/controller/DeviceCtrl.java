package com.example.GestioneDispositiviAziendali.controller;

import com.example.GestioneDispositiviAziendali.exceptionHandler.NotFoundException;
import com.example.GestioneDispositiviAziendali.model.entities.Device;
import com.example.GestioneDispositiviAziendali.model.request.DevForWorkReq;
import com.example.GestioneDispositiviAziendali.model.request.DeviceRequest;
import com.example.GestioneDispositiviAziendali.service.DeviceSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeviceCtrl {

    @Autowired
    private DeviceSvc deviceSvc;

    @GetMapping("/device")
    public ResponseEntity<CustomResponse> getAllDevices(Pageable pageable) {
        Page<Device> devices = deviceSvc.getAllDevices(pageable);
        return CustomResponse.success(HttpStatus.OK.toString(),devices,HttpStatus.OK);
    }

    @GetMapping("/device/{id}")
    public ResponseEntity<CustomResponse> getDeviceById(@PathVariable int id) throws NotFoundException {
        Device device = deviceSvc.getDevById(id);
        return CustomResponse.success(HttpStatus.OK.toString(),device,HttpStatus.OK);
    }

    @PostMapping("/device")
    public ResponseEntity<CustomResponse> createDevice(@RequestBody @Validated DeviceRequest deviceRequest,
                                                     BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage()).toList().toString();
            return CustomResponse.failure(errorMessage,HttpStatus.BAD_REQUEST);
        }
        Device device = deviceSvc.createDevice(deviceRequest);
        return CustomResponse.success(HttpStatus.CREATED.toString(),device,HttpStatus.CREATED);
    }

    @PutMapping("/device/assign/{id}")
    public ResponseEntity<CustomResponse> assignDevice(@PathVariable int id,
                                                       @RequestBody @Validated DevForWorkReq dev,
                                                       BindingResult bindingResult) throws NotFoundException {

        if (bindingResult.hasErrors()){
            String errorMSg = bindingResult.getAllErrors().stream()
                    .map(objectError ->objectError.getDefaultMessage()).toList().toString();
            return CustomResponse.failure(errorMSg,HttpStatus.BAD_REQUEST);
        }

        Device device = deviceSvc.upDateWorkerToDevice(id,dev);
        return CustomResponse.success(HttpStatus.OK.toString(),device,HttpStatus.OK);
    }

    @PutMapping("/device/{id}")
    public ResponseEntity<CustomResponse> upDateDevice (@PathVariable int id,
                                                        @RequestBody @Validated DeviceRequest deviceRequest,
                                                        BindingResult bindingResult) throws NotFoundException {

        if (bindingResult.hasErrors()){
            String errorMSg = bindingResult.getAllErrors().stream()
                    .map(objectError ->objectError.getDefaultMessage()).toList().toString();
            return CustomResponse.failure(errorMSg,HttpStatus.BAD_REQUEST);
        }

        Device device = deviceSvc.upDateDevice(id,deviceRequest);
        return CustomResponse.success(HttpStatus.OK.toString(),device,HttpStatus.OK);
    }




    @DeleteMapping("/device/{id}")
    public ResponseEntity<CustomResponse> deleteDevice(@PathVariable int id) throws NotFoundException {
        deviceSvc.deleteDevice(id);
        return CustomResponse.emptyResponse("Device with id " + id + " is deleted",HttpStatus.OK);
    }
}
