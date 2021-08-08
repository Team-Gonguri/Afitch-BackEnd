package com.project.auth.model.dto;

import com.project.security.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpDto {
    private String accountId;
    private String password;
    private String nickName;
    private UserRole role;
    private String adminCode;
}
