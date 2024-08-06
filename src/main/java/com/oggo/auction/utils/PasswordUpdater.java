package com.oggo.auction.utils;

import com.oggo.auction.model.Users;
import com.oggo.auction.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PasswordUpdater implements CommandLineRunner {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<Users> users = repository.findAll();
        for (Users user : users) {
            if (!user.getPassword().startsWith("$2a$")) { // BCrypt로 인코딩된 비밀번호는 $2a$로 시작합니다.
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                repository.save(user);
            }
        }
    }
}
