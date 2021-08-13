package com.project.auth.model.dto;

import com.project.security.JwtTokenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenDto {
    private JwtTokenDto access;
    private JwtTokenDto refresh;
}
