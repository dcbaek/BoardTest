package com.example.boardtest.Service;

import com.example.boardtest.domain.user.User;
import com.example.boardtest.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository; //생성자 주입을 받기 위해 @RequiredArgsConstructor 어노테이션을 썼습니다.
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원가입로직
     */

    @Transactional
    public Long save(User user) {
        String hashPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPw);
        return userRepository.save(user).getId();
    }
}
