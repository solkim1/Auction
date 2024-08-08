package com.oggo.auction.controller;

import com.oggo.auction.model.Comment;
import com.oggo.auction.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{userId}")
    public List<Comment> getComments(@PathVariable String userId) {
        System.out.println("Received request with userId: " + userId);
        return commentService.getCommentsByUserId(userId);
    }

    @GetMapping("/count/{userId}")
    public ResponseEntity<Map<String, Integer>> getCommentCount(@PathVariable String userId) {
        int count = commentService.getCommentCountByUserId(userId);
        Map<String, Integer> response = new HashMap<>();
        response.put("count", count);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.saveComment(comment);
        return ResponseEntity.ok(savedComment);
    }
}