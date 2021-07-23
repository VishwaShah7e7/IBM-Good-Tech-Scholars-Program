package com.gtsp.gtsp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtsp.gtsp.dto.DeviceDto;
import com.gtsp.gtsp.service.DeviceRegistrationService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/devices")
@AllArgsConstructor
@Slf4j
public class DevicesController {
	
    private final DeviceRegistrationService deviceRegistrationService;
	
	@PostMapping
    public ResponseEntity<DeviceDto> addDevice(@RequestBody DeviceDto deviceDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deviceRegistrationService.save(deviceDto));
    }

}
