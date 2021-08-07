package com.songi.working.service;

import com.songi.working.dto.UserChangePasswordRequest;
import com.songi.working.dto.UserSignUpRequest;
import com.songi.working.entity.User;
import com.songi.working.repository.UserRepository;
import com.songi.working.result.error.BusinessException;
import com.songi.working.result.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(UserSignUpRequest userSignUpRequest) {
        userRepository.save(userSignUpRequest.toEntity());
    }

    @Override
    public void changePassword(UserChangePasswordRequest userChangePasswordRequest) {
        User user = userRepository.findById(userChangePasswordRequest.getUserId())
                .orElseThrow(() -> new BusinessException("해당 ID 유저가 없습니다.", ErrorCode.NOT_FOUND));
        if (!user.getPassword().equals(userChangePasswordRequest.getPassword())) {
            throw new BusinessException("기존 비밀번호가 맞지 않습니다.", ErrorCode.INVALID_INPUT_VALUE);
        }
        user.changePassword(userChangePasswordRequest.getNewPassword());
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("해당 ID 유저가 없습니다.", ErrorCode.NOT_FOUND));
        userRepository.delete(user);
    }
}
