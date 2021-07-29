package com.project.security;

import com.project.security.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AuthInfo {
    private Long id;
    private List<UserRole> authorities;
}
