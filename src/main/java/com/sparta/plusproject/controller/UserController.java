package com.sparta.plusproject.controller;

import com.sparta.plusproject.dto.SignupRequestDto;
import com.sparta.plusproject.dto.SignupResponseDto;
import com.sparta.plusproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public String signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        userService.signup(requestDto, bindingResult);
        return "index";
    }
}
