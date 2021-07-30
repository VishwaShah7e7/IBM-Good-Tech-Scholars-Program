package com.gtsp.gtsp.controller;


import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtsp.gtsp.dto.NotificationResponse;
import com.gtsp.gtsp.model.User;
import com.gtsp.gtsp.service.AuthService;
import com.gtsp.gtsp.service.NotificationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/notification")
@AllArgsConstructor
public class NotificationController {
	
	 private final NotificationService notificationService;
	 private final AuthService authService;
	
	@GetMapping
    public NotificationResponse getRecommandation() {
		User user = authService.getCurrentUser();
		return notificationService.getRecommandation(user);
	}

}
