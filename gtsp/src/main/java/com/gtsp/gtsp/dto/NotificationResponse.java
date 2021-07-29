package com.gtsp.gtsp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

	private long userId;
	private String message;
	private double outSideTemp;
	private double desiredTemp;
	
	
	
}
