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
import com.gtsp.gtsp.dto.RefreshTokenRequest;
import com.gtsp.gtsp.dto.RegisterRequest;
import com.gtsp.gtsp.service.AuthService;
import com.gtsp.gtsp.service.RefreshTokenService;

import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
	
	    private final AuthService authService;
	    private final RefreshTokenService refreshTokenService;
	    //Logger logger = LoggerFactory.getLogger(AuthController.class);
	    
	    @GetMapping("/")
	    public String home() {
	    	//logger.debug("Welcomed the user ");
	        return ("<h1>Welcome</h1>");
	    }
	    
		//@PostMapping("/signup")
	    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
	    	//logger.debug("Email : " +  registerRequest.getEmail());
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
	    
	    @PostMapping("/refresh/token")
	    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
	        return authService.refreshToken(refreshTokenRequest);
	    }

	    @PostMapping("/logout")
	    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
	        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
	        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
	    }
	    
	    
}
