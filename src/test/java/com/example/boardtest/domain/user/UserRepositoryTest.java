package com.example.boardtest.domain.user;

import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입_테스트() {
        String username = "test";
        String nickname = "babo";

        userRepository.save(User.builder()
                .username(username)
                .password("1234")
                .email("test@1234.com")
                .nickname(nickname)
                .role(Role.USER)
                .build());

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getNickname()).isEqualTo(nickname);
    }

}