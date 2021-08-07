package com.songi.working.repository;

import com.songi.working.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    User user, saveUser;

    @BeforeEach
    void setUp() {
        user = User.builder().id("id").password("password").build();
        saveUser = userRepository.save(user);
    }

    @DisplayName("유저 저장(회원 가입)")
    @Transactional
    @Test
    void save() {
        assertThat(user.getId()).isEqualTo(saveUser.getId());
        assertThat(user.getPassword()).isEqualTo(saveUser.getPassword());
    }

    @DisplayName("아이디 비밀번호 확인")
    @Transactional
    @Test
    void findUser() {
        User find = userRepository.findById("id").get();
        assertThat(find.getPassword()).isEqualTo("password");
    }

    @Transactional
    @DisplayName("유저 삭제")
    @Test
    void delete() {
        userRepository.deleteById("id");
        assertThat(userRepository.findById("id").isEmpty()).isTrue();
    }


}