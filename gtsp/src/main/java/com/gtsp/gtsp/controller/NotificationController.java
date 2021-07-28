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
import com.gtsp.gtsp.service.NotificationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/notification")
@AllArgsConstructor
public class NotificationController {
	
	 private final NotificationService notificationService;
	
	@GetMapping
    public NotificationResponse getRecommandation(@PathParam(value = "user_id") long user_id) {
		System.out.println("Passed userID: " +  user_id);
		return notificationService.getRecommandation(user_id);
        /*return ResponseEntity.status(HttpStatus.OK)
                .body(notificationService.getRecommandation(user_id));*/
	}

}
