package com.gtsp.gtsp.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gtsp.gtsp.dto.NotificationResponse;
import com.gtsp.gtsp.model.User;
import com.gtsp.gtsp.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class NotificationService {
	
	
	private UserRepository userRepository;
	
	public NotificationResponse getRecommandation(long user_id) {
		
		Optional<User> user = userRepository.findById(user_id);
		
		// call the weather API to get the outside temp using the location we get from the USer object. 
		
		// database call to get the inside temp for the given user_id 
		
		
		
		int outsideTemp = 80;
		int insideTemp = 77;
		int setTemp = 76; // set by the user using our application. 
		int difference = 5;
		

		
		if(insideTemp == setTemp && Math.abs(setTemp - outsideTemp) < difference) {
			return new NotificationResponse(user_id,"Do nothing");
		}
		if (insideTemp >= setTemp) {
			if (Math.abs(setTemp - outsideTemp) > difference) {
				return new NotificationResponse(user_id,"Turn on HVAC and let the room cool");
			} else {
				return new NotificationResponse(user_id,"Open the window. Let the room heat to DT");
			}
		} else {
			if (Math.abs(setTemp - outsideTemp) > difference) {
				return new NotificationResponse(user_id,"Turn on heat and let the room heat until DT");
			} else {
				return new NotificationResponse(user_id,"Wear a jacket");
			}
		} 
		
	}
	

}
