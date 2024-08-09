package com.oggo.auction.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "profilecomments")
public class Comment {
    // Getter and Setter methods
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_idx")
    private Long commentIdx;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "comment_content", nullable = false)
    private String commentContent;

    @Column(name = "commented_at", nullable = false, updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date commentedAt;

    @Column(name = "seller_id", nullable = false)
    private String sellerId;

}
