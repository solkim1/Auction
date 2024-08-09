package com.oggo.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oggo.auction.model.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
    Users findByUserId(String userId);
    Users findByUserIdAndPassword(String userId, String password);
}
