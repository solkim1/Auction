package com.oggo.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oggo.auction.model.Users;
import com.oggo.auction.service.UsersService;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService service;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody Users member) {
        service.join(member);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody Users member) {
        System.out.println("Received login request: " + member);
        Users result = service.login(member);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            System.out.println("Login failed for user: " + member.getUserId() + member.getPassword());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
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
}
