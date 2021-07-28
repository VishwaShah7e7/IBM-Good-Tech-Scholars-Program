package com.gtsp.gtsp.service;



import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gtsp.gtsp.dto.NotificationResponse;
import com.gtsp.gtsp.exception.GtspException;
import com.gtsp.gtsp.model.User;
import com.gtsp.gtsp.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NotificationService {
	
	
	private UserRepository userRepository;
	
	public static String API_KEY = "e11b2f7bc8cf815a54c7b06d6883fe83";
	
	@Transactional(readOnly = true)
    private User getPost(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new GtspException("Id not found......" + id.toString()));
        return user;
    }
	
	public NotificationResponse getRecommandation(long user_id) {
			
		User user = getPost(user_id);	
		double outsideTemp = getOutsideTemp(user.getZipCode());
		int insideTemp = getInsideTemp();
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
	
	private double getOutsideTemp(long zipCode) {
		double outsideTemp = 0;
		if(zipCode == 0) {
			zipCode = 95112;
		}
		String urlString = "https://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + "&appid=" + API_KEY + "&units=imperial";
		try{
			
			URL url = new URL(urlString);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setRequestProperty("Accept", "application/json");

			System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			
			String response = new String(http.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			System.out.println(response);
			http.disconnect();
			
			
			Map<String, Object > respMap = jsonToMap (response);
	        Map<String, Object > mainMap = jsonToMap (respMap.get("main").toString());
	        return Double.valueOf(mainMap.get("temp").toString());

	      }catch (IOException e){
	          System.out.println(e.getMessage());
	      }
		return outsideTemp;
	}
	
	public Map<String,Object> jsonToMap(String str){
	    Map<String,Object> map = new Gson().fromJson(str,new 
	TypeToken<HashMap<String,Object>> () {}.getType());
	    return map;
	}
	
	private int getInsideTemp() {
		try {
			File fr = new File("/Users/vishwashah/Desktop/test.txt");
			Scanner sc = new Scanner(fr);
			while (sc.hasNextLine()) {
				return Integer.valueOf(sc.nextLine());
			}
		} catch (IOException e) {
			System.err.println("FileCopy: " + e);
		}
		return 0;
	}
	

}
