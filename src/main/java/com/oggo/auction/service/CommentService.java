package com.oggo.auction.service;

import com.oggo.auction.model.Comment;
import com.oggo.auction.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByUserId(String userId) {
        return commentRepository.findByUserId(userId);
    }

    public int getCommentCountByUserId(String userId) {
        return commentRepository.countCommentsByUserId(userId);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
