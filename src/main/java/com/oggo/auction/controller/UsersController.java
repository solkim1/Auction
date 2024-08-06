package com.oggo.auction.controller;

import com.oggo.auction.model.Users;
import com.oggo.auction.service.UsersService;
import com.oggo.auction.utils.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody Users member) {
        service.join(member);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users member) {
        System.out.println("Received login request: " + member);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getUserId(), member.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.createToken(userDetails.getUsername());

        if (token != null) {
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            System.out.println("Login failed for user: " + member.getUserId());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = service.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found in database.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(users);
        } else {
            System.out.println("All users in database:");
            for (Users user : users) {
                System.out.println(user);
            }
            return ResponseEntity.ok(users);
        }
    }

    @Setter
    @Getter
    static class JwtResponse {
        private String token;

        public JwtResponse(String token) {
            this.token = token;
        }

    }
}
