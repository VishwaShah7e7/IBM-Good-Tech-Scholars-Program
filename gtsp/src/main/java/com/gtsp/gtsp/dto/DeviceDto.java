package com.gtsp.gtsp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {
	private Long id;
	private DeviceType deviceType ;
	private String description;

}
