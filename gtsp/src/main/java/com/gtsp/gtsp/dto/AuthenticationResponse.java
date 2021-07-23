package com.gtsp.gtsp.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

	
	/*public AuthenticationResponse(String authenticationToken, String username){
		this.authenticationToken =  authenticationToken;
		this.username = username;
	}*/
	private String authenticationToken;
    //private String refreshToken;
    //private Instant expiresAt;
    private String username;
}
