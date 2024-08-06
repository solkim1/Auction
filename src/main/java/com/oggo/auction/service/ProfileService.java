package com.oggo.auction.service;

import com.oggo.auction.repository.CommentRepository;
import com.oggo.auction.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CommentRepository commentRepository;

    public String extractUserIdFromToken(String token) {
        return jwtUtil.extractUsername(token.replace("Bearer ", ""));
    }

    public int getCommentCountByUserId(String userId) {
        return commentRepository.countCommentsByUserId(userId);
    }
}
