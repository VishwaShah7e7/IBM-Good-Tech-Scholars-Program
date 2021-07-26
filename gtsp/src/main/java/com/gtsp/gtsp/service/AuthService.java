package com.gtsp.gtsp.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gtsp.gtsp.controller.AuthController;
import com.gtsp.gtsp.dto.AuthenticationResponse;
import com.gtsp.gtsp.dto.LoginRequest;
import com.gtsp.gtsp.dto.RegisterRequest;
import com.gtsp.gtsp.exception.GtspException;
import com.gtsp.gtsp.model.NotificationEmail;
import com.gtsp.gtsp.model.User;
import com.gtsp.gtsp.model.VerificationToken;
import com.gtsp.gtsp.repository.UserRepository;
import com.gtsp.gtsp.repository.VerificationTokenRepository;
import com.gtsp.gtsp.security.JwtProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	
	private final PasswordEncoder passwordEncoder;
	
	
	private final UserRepository userRepository;

	private final VerificationTokenRepository verificationTokenRepository;

	private final MailService mailService;
	
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    //Logger logger = LoggerFactory.getLogger(AuthService.class);
	
	@Transactional
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setUsername(registerRequest.getUserName());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(false);
		
		userRepository.save(user);	
		//logger.info("User saved successfully");
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to IBM Good Tech Scholar Program, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8085/api/auth/accountVerification/" + token));
	}
	
	private String generateVerificationToken(User user){
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
		
	}
	
	public void verifyToken(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		verificationToken.orElseThrow(() -> new GtspException("Invalid Token"));
		fetchUserAndEnable(verificationToken.get());
	}
	
	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		String userName = verificationToken.getUser().getUsername();
		User user = userRepository.findByUsername(userName).orElseThrow(() -> new GtspException("User Not foudn with name" + userName));
		user.setEnabled(true);
		userRepository.save(user);	
	}
	
	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token,loginRequest.getUsername());
        /*return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();*/
	}
}
