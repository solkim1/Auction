package com.oggo.auction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

    @Column(name = "likes")
    private Integer likes = 0;

    // Getters and setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
