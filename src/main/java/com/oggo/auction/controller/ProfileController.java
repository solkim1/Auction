package com.oggo.auction.controller;

import com.oggo.auction.model.Users;
import com.oggo.auction.service.ProfileService;
import com.oggo.auction.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UsersService usersService;

    @GetMapping
    public Users getProfile(@RequestHeader("Authorization") String token) {
        String userId = profileService.extractUserIdFromToken(token);
        return usersService.findUserById(userId);
    }

    @GetMapping("/comments/count/{userId}")
    public int getCommentCount(@PathVariable String userId) {
        return profileService.getCommentCountByUserId(userId);
    }

    @PostMapping("/likes/increment/{userId}")
    public Users incrementLikes(@PathVariable String userId) {
        return usersService.incrementLikes(userId);
    }
}
