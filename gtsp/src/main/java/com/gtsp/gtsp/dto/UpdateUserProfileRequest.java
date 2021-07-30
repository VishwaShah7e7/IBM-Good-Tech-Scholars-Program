package com.gtsp.gtsp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileRequest {
	private String userName;
	private long zipCode;
	private double desiredTemp;
}
