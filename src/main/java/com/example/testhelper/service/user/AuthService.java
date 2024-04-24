package com.example.testhelper.service.user;

import com.example.testhelper.config.JwtTokenProvider;
import com.example.testhelper.domain.user.User;
import com.example.testhelper.domain.user.UserRepository;
import com.example.testhelper.dto.user.AuthDto;
import com.example.testhelper.exception.ApiException;
import com.example.testhelper.exception.enums.CommonExceptionEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public AuthDto.LoginResponse signIn(AuthDto.LoginRequest loginRequest) throws RuntimeException {

        User user = userRepository.getByEmail(loginRequest.getEmail());

        if(user == null || !passwordEncoder.matches(loginRequest.getUserPassword(), user.getUserPassword())){
            throw new ApiException(CommonExceptionEnum.AUTHORIZATION_FAILED);
        }

        String token = jwtTokenProvider.createToken(String.valueOf(user.getEmail()));

        user.saveToken(token);

        return AuthDto.LoginResponse.builder()
                .accessToken(token)
                .user(user)
                .build();
    }

    public AuthDto.LoginResponse testLogin() {
        User user = userRepository.getTestUser();
        String token = jwtTokenProvider.createToken(String.valueOf(user.getEmail()));

        return AuthDto.LoginResponse.builder()
                .accessToken(token)
                .user(user)
                .build();
    }

    @Transactional
    public String getTestTokenById(Integer id) {
        User user = userRepository.getReferenceById(id);
        return jwtTokenProvider.createToken(String.valueOf(user.getEmail()));
    }

    @Transactional
    public void logout(String accessToken) {
        // 1. Access Token 검증
        if (!jwtTokenProvider.validateToken(accessToken)) {
            throw new ApiException(CommonExceptionEnum.AUTHORIZATION_FAILED);
        }

        // 2. Access Token 에서 authentication 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        // 3. DB에 저장된 Token 제거
        String userEmail = authentication.getName();
        User user = userRepository.getByEmail(userEmail);
        user.removeToken();
    }

}
