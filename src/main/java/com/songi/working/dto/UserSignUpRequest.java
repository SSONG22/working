package com.songi.working.dto;

import com.songi.working.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor
public class UserSignUpRequest {
    protected String userId;
    protected String password;

    public UserSignUpRequest(final String userId, final String password){
        this.userId = userId;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .id(userId)
                .password(password)
                .build();
    }
}
