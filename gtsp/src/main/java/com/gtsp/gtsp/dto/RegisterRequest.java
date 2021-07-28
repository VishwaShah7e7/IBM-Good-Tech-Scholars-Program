package com.gtsp.gtsp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
     private String email;
     private String userName;
     private String password;
     private long zipCode;
     
}
