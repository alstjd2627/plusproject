package com.sparta.plusproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @NotBlank
    @Size(min=3)
    private String username;
    @NotBlank
    @Size(min=4)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password;
    @NotBlank
    @Size(min=4)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String repassword;
}
