package com.songi.working.service;

import com.songi.working.dto.UserChangePasswordRequest;
import com.songi.working.dto.UserSignUpRequest;
import com.songi.working.entity.User;
import com.songi.working.repository.UserRepository;
import com.songi.working.result.error.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp(){
        UserSignUpRequest request = UserSignUpRequest.builder()
                .userId("id")
                .password("password")
                .build();
        userService.saveUser(request);
    }

    @Test
    void saveUser() {
        User findUser = userRepository.findById("id").orElse(null);

        assertThat(findUser.getId()).isEqualTo("id");
        assertThat(findUser.getPassword()).isEqualTo("password");
    }

    @Test
    void changePassword() {
        UserChangePasswordRequest request = UserChangePasswordRequest.builder()
                .userId("id")
                .password("password")
                .newPassword("password2")
                .build();
        userService.changePassword(request);
        User findUser = userRepository.findById("id").orElse(null);

        assertThat(findUser.getId()).isEqualTo("id");
        assertThat(findUser.getPassword()).isEqualTo("password2");
    }

    @DisplayName("비밀번호가 일치하지 않을때")
    @Test
    void changePasswordError() {
        UserChangePasswordRequest request = UserChangePasswordRequest.builder()
                .userId("id")
                .password("password2")
                .newPassword("password")
                .build();
        assertThatExceptionOfType(BusinessException.class).isThrownBy(()-> userService.changePassword(request));
    }

    @DisplayName("회원 삭제")
    @Test
    void deleteUser(){
        userService.deleteUser("id");
        User user = userRepository.findById("id").orElse(null);
        assertThat(user).isEqualTo(null);
    }

    @DisplayName("회원 삭제 싪패 - id가 없을때")
    @Test
    void deleteUserError() {
        assertThatExceptionOfType(BusinessException.class).isThrownBy(()-> userService.deleteUser("test"));
    }

}