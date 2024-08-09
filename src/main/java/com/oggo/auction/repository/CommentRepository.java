package com.oggo.auction.repository;

import com.oggo.auction.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.userId = :userId")
    int countCommentsByUserId(@Param("userId") String userId);
    @Query("SELECT c FROM Comment c WHERE c.userId = :userId")
    List<Comment> findByUserId(@Param("userId") String userId);
}
