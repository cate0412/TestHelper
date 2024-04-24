package com.example.testhelper.http.controller.user;

import com.example.testhelper.domain.user.User;
import com.example.testhelper.dto.user.UserRequestDto;
import com.example.testhelper.dto.user.UserResponseDto;
import com.example.testhelper.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController implements UserApi{

    private final UserService userService;

    @PostMapping("")
    public UserResponseDto.UserInfo createUser(@Valid @RequestBody UserRequestDto.Create requestDto) {
        return userService.createUser(requestDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto.UserInfo getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PutMapping("/withdraw/{id}")
    public Integer withdrawUser(@AuthenticationPrincipal User user,
                                @PathVariable Integer id){
        return userService.withdrawUser(user);
    }

    @PutMapping("/update/{id}")
    public UserResponseDto.UserInfo updateUser(@AuthenticationPrincipal User user,
                                               @PathVariable Integer id,
                                               @Valid @RequestBody UserRequestDto.Update requestDto){
        return userService.updateUser(user, requestDto);
    }

    @PutMapping("/update/password/{id}")
    public Integer updateUserPassword(@AuthenticationPrincipal User user,
                                      @PathVariable Integer id,
                                      @Valid @RequestBody UserRequestDto.PasswordUpdate PwRequestDto){
        return userService.updateUserPassword(id, PwRequestDto);
    }
}
