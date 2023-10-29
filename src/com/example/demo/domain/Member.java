package com.example.demo.domain;

import com.example.demo.domain.types.MemberStatus;

import java.time.Instant;

public class Member {
    private Long id;

    public String username;
    public String password;
    public String nickname;
    public MemberStatus status;

    public Instant createdAt;
    public Instant updatedAt;

    public Member(Long id, String username, String password, String nickname, MemberStatus status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
