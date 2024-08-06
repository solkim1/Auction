package com.oggo.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oggo.auction.model.Users;
import com.oggo.auction.repository.UsersRepository;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repository;

    // 사용자 저장 (회원가입)
    public void join(Users member) {
        repository.save(member);
    }

    // 로그인 기능
    public Users login(Users member) {
        Users result = repository.findByUserIdAndPassword(member.getUserId(), member.getPassword());
        return result;
    }

    // 모든 사용자 가져오기
    public List<Users> getAllUsers() {
        return repository.findAll();
    }
}
