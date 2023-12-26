package com.sparta.plusproject.service;

import com.sparta.plusproject.dto.LoginRequestDto;
import com.sparta.plusproject.dto.SignupRequestDto;
import com.sparta.plusproject.dto.SignupResponseDto;
import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.jwt.JwtUtil;
import com.sparta.plusproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public SignupResponseDto signup(SignupRequestDto requestDto, BindingResult bindingResult) throws Exception {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
        }
        if(requestDto.getPassword().contains(requestDto.getUsername())
                || requestDto.getRepassword().contains(requestDto.getUsername())){
            throw new Exception("비밀번호에 닉네임이 포함되어 있습니다.");
        }

        if(!requestDto.getRepassword().equals(requestDto.getPassword())){
            throw new Exception("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        if(userRepository.findByUsername(requestDto.getUsername()).isPresent()){
            throw new Exception("닉네임이 중복됩니다.");
        }


        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());



        User user = new User(username, password);
        // 사용자 등록
        userRepository.save(user);
        return new SignupResponseDto(user);
    }

    public void login(LoginRequestDto requestDto,HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(user.getUsername());
        jwtUtil.addJwtToCookie(token, res);


    }
}
