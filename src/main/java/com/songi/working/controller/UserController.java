package com.songi.working.controller;

import com.songi.working.dto.UserDeleteRequest;
import com.songi.working.result.ResponseResult;
import com.songi.working.dto.UserChangePasswordRequest;
import com.songi.working.dto.UserSignUpRequest;
import com.songi.working.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    /*
    회원 가입 기능
     */
    @PostMapping
    public ResponseEntity<ResponseResult> signUp(@RequestBody final UserSignUpRequest userSignUpRequest) {
        userService.saveUser(userSignUpRequest);
        return ResponseEntity.ok(ResponseResult.success("회원 가입에 성공했습니다."));
    }

    /*
    회원의 password 변경 기능
     */
    @PatchMapping
    public ResponseEntity changePassword(@RequestBody final UserChangePasswordRequest userChangePasswordRequest) {
        userService.changePassword(userChangePasswordRequest);
        return ResponseEntity.ok(ResponseResult.success("비밀번호 변경에 성공했습니다."));
    }

    /*
    회원 삭제 기능
     */
    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody final UserDeleteRequest userDeleteRequest) {
        userService.deleteUser(userDeleteRequest.getUserId());
        return ResponseEntity.ok(ResponseResult.success("해당 유저를 삭제했습니다."));
    }
}
