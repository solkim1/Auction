package com.oggo.auction.service;

import com.oggo.auction.model.Users;
import com.oggo.auction.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 사용자 저장 (회원가입)
    public void join(Users member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        repository.save(member);
    }

    // 로그인 기능
    public Users login(Users member) {
        Users result = repository.findByUserId(member.getUserId());
        if (result != null && passwordEncoder.matches(member.getPassword(), result.getPassword())) {
            return result;
        } else {
            return null;
        }
    }

    // 모든 사용자 가져오기
    public List<Users> getAllUsers() {
        return repository.findAll();
    }

    // 사용자 정보 가져오기
    public Users findUserById(String userId) {
        return repository.findByUserId(userId);
    }
}
