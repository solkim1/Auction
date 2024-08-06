package com.oggo.auction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users") // 테이블 이름을 명시적으로 지정
public class Users {
    // Getter and Setter methods
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "likes")
    private Integer likes = 0;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
