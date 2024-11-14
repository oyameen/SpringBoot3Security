package com.oyameen.SpringBootSecurity3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class LoginDto {
    private String userName;
    private String userEmail;
    private String password;
}
