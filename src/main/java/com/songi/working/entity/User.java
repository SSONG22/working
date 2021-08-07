package com.songi.working.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    private String id;
    @NotNull
    private String password;

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
