package com.songi.working.service;

import com.songi.working.dto.UserChangePasswordRequest;
import com.songi.working.dto.UserSignUpRequest;

public interface UserService {
    void saveUser(UserSignUpRequest userSignUpRequest);
    void changePassword(UserChangePasswordRequest userChangePasswordRequest);
    void deleteUser(String userId);
}
