package com.gtsp.gtsp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtsp.gtsp.dto.AuthenticationResponse;
import com.gtsp.gtsp.dto.LoginRequest;
import com.gtsp.gtsp.dto.RegisterRequest;
import com.gtsp.gtsp.service.AuthService;

import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.MediaType;


@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
	
	    private final AuthService authService;
		//@PostMapping("/signup")
	    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
			System.out.println("Email : " +  registerRequest.getEmail());
			authService.signup(registerRequest);
			return new ResponseEntity<>("User Registration Successful",OK);
		}
	    
	    @GetMapping("accountVerification/{token}")
	    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
	        authService.verifyToken(token);
	        return new ResponseEntity<>("Account Activated Successfully", OK);
	    }
	    
	    @PostMapping("/login")
	    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
	        return authService.login(loginRequest);
	    }
}