package com.gtsp.gtsp.service;

import static org.springframework.http.HttpStatus.OK;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtsp.gtsp.dto.UpdateUserProfileRequest;
import com.gtsp.gtsp.exception.GtspException;
import com.gtsp.gtsp.model.User;
import com.gtsp.gtsp.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDetailService {
	
	private final UserRepository userRepository;
	
	@Transactional(readOnly = true)
    private User getUser(String userName) {
		User user = userRepository.findByUsername(userName).orElseThrow(() -> new GtspException("Id not found......" + userName));
        return user;
    }
	

	public ResponseEntity updateUserDetail(UpdateUserProfileRequest updateUserProfileRequest)  {
        updateUser(getUser(updateUserProfileRequest.getUserName()), updateUserProfileRequest);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@Transactional
	public void updateUser(User u,UpdateUserProfileRequest  updateUserProfileRequest) {
	    // crush the variables of the object found
	    u.setDesiredTemp(updateUserProfileRequest.getDesiredTemp());
	    u.setZipCode(updateUserProfileRequest.getZipCode());
	    userRepository.save(u);
	}

}
