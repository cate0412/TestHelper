package com.example.testhelper.service.user;

import com.example.testhelper.domain.system.Code;
import com.example.testhelper.domain.system.CodeRepository;
import com.example.testhelper.domain.user.User;
import com.example.testhelper.domain.user.UserRepository;
import com.example.testhelper.dto.user.UserRequestDto;
import com.example.testhelper.dto.user.UserResponseDto;
import com.example.testhelper.enums.UserStatusEnum;
import com.example.testhelper.exception.ApiException;
import com.example.testhelper.exception.enums.CommonExceptionEnum;
import com.example.testhelper.exception.enums.UserExceptionEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CodeRepository codeRepository;
    private final ApplicationEventPublisher eventPublisher;

    public UserResponseDto.UserInfo getUserById(int id) {
        User user = userRepository.getById(id);
        return new UserResponseDto.UserInfo(user);
    }

    @Transactional
    public UserResponseDto.UserInfo createUser(UserRequestDto.Create createDto) {

        this.checkUserEmail(createDto.getEmail());
        String encryptedPassword = passwordEncoder.encode(createDto.getUserPassword());
        Code statusCode = codeRepository.getReferenceById(createDto.getStatus());

        User newUser = userRepository.save(User.builder()
                .email(createDto.getEmail())
                .userPassword(encryptedPassword)
                .name(createDto.getName())
                .gender(createDto.getGender())
                .status(statusCode)
                .build());


        return new UserResponseDto.UserInfo(newUser);

    }

    @Transactional
    public UserResponseDto.UserInfo updateUser(User user, UserRequestDto.Update updateDto) {
        String updateEmail = updateDto.getEmail();
        String updateName = updateDto.getName();
        String updateGender = updateDto.getGender();

        if(updateEmail != null && !updateEmail.equals(user.getEmail())) {
            this.checkUserEmail(updateEmail);
        }

        user.update(
                updateEmail,
                updateName,
                updateGender
        );
        userRepository.save(user);

        return new UserResponseDto.UserInfo(user);
    }

    public void checkUserEmail(String email) {
        User user = userRepository.getByEmail(email);

        if (user != null) {
            throw new ApiException(UserExceptionEnum.USER_EXISTS);
        }
    }

    @Transactional
    public Integer withdrawUser(User user) {
        Code withdrawCode = codeRepository.getReferenceById(UserStatusEnum.WITHDRAW.code());
        user.withdraw(withdrawCode);
        userRepository.save(user);

        return user.getId();
    }

    @Transactional
    public Integer updateUserPassword(Integer userId, UserRequestDto.PasswordUpdate updatePasswordDto) {
        this.checkUser(updatePasswordDto.getEmail(), updatePasswordDto.getName());

        String newPassword = updatePasswordDto.getUserPassword();
        String encodedPassword = passwordEncoder.encode(newPassword);
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException(CommonExceptionEnum.NOT_FOUND));
        user.updatePassword(encodedPassword);

        return user.getId();
    }

    public void checkUser(String email, String name) {
        User user = userRepository.findByEmailAndName(email, name);

        if (user == null) {
            throw new ApiException(CommonExceptionEnum.NOT_FOUND);
        }
    }
}
