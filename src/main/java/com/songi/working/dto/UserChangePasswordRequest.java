package com.songi.working.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
public class UserChangePasswordRequest extends UserSignUpRequest {
    private String newPassword;

    public UserChangePasswordRequest(final String userId, final String password, final String newPassword){
        super(userId, password);
        this.newPassword = newPassword;
    }
}
