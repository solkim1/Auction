package com.oggo.auction.service;

import com.oggo.auction.model.Users;
import com.oggo.auction.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUserId(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getUserId(), user.getPassword(), Collections.emptyList());
    }
}
