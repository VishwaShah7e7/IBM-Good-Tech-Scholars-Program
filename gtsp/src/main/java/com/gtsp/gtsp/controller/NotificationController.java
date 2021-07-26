package com.gtsp.gtsp.controller;

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
	
	@PostMapping("/")
    public NotificationResponse getRecommandation(@RequestBody long user_id) {
        return notificationService.getRecommandation(user_id);
	}

}
