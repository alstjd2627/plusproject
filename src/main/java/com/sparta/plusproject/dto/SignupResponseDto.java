package com.sparta.plusproject.dto;

import com.sparta.plusproject.entity.User;

public class SignupResponseDto {
    private long userId;

    public SignupResponseDto(User user){
        this.userId = user.getId();
    }
}
