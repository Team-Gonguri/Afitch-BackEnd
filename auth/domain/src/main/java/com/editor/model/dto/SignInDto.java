package com.editor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInDto {
    private String accountId;
    private String password;
}
