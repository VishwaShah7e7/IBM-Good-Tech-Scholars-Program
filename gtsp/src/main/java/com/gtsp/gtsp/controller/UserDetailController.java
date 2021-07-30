package com.gtsp.gtsp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtsp.gtsp.dto.DeviceDto;
import com.gtsp.gtsp.dto.UpdateUserProfileRequest;
import com.gtsp.gtsp.model.User;
import com.gtsp.gtsp.service.AuthService;
import com.gtsp.gtsp.service.DeviceRegistrationService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/userDetail")
@AllArgsConstructor
@Slf4j
public class UserDetailController {
	
    private final com.gtsp.gtsp.service.UserDetailService UserDetailService;
    private final AuthService authService;
	
	@PutMapping
    public ResponseEntity addDevice(@RequestBody UpdateUserProfileRequest updateUserProfileRequest) {
        return UserDetailService.updateUserDetail(updateUserProfileRequest);
    }
	
	/*@GetMapping("/all")
	public ResponseEntity<List<DeviceDto>> getAllDevices() {
		User user = authService.getCurrentUser();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deviceRegistrationService.save(deviceDto));
    }*/

}
