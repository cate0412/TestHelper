package com.example.testhelper.http.controller.auth;

import com.example.testhelper.config.JwtTokenProvider;
import com.example.testhelper.domain.user.User;
import com.example.testhelper.domain.user.UserRepository;
import com.example.testhelper.dto.AuthDto;
import com.example.testhelper.exception.ApiException;
import com.example.testhelper.exception.enums.CommonExceptionEnum;
import com.example.testhelper.service.user.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthApi{

    private final AuthService authService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public AuthDto.LoginResponse login(@Valid @RequestBody AuthDto.LoginRequest loginRequest) {
        User user = userRepository.getByEmail(loginRequest.getEmail());

        if (!passwordEncoder.matches(loginRequest.getUserPassword(), user.getUserPassword())) {
            throw new ApiException(CommonExceptionEnum.AUTHORIZATION_FAILED);
        }
        return authService.signIn(loginRequest);
    }

    @PostMapping("/testLogin")
    public AuthDto.LoginResponse testLogin() {
        return authService.testLogin();
    }


    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        authService.logout(token);

    }
}
