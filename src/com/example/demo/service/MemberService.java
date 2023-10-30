package com.example.demo.service;

import com.example.demo.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberService {
    private final List<Member> memberRepository;
    private final MemberIdSequenceManager sequenceManager;

    public MemberService() {
        this.memberRepository = new ArrayList<>();
        this.sequenceManager = new MemberIdSequenceManager();
    }

    public Member signUp(String username, String password, String nickname) {
        long id = sequenceManager.increaseAndGetId();
        // TODO register the account.
        return null;
    }

    public List<Member> getAllMembers() {
        // 변경할 수 없는 사본을 제공.
        return List.copyOf(memberRepository);
    }

    // 회원가입할때  리스트내에  닉네임/풀네임이 있는지 없는지 판별하는 기능.
    public boolean serverUsername(String username) {
        for (var eachMember : memberRepository) {
            boolean exists = Objects.equals(eachMember.username, username);// 회원가입시 동일한 유저닉네임이 있을시 false로 판별.
            if (exists) {
                return true;
            }
        }
        return false;
    }

    public boolean serverNickname(String nickname) {
        for (var eachMember : memberRepository) {
            boolean exists = Objects.equals(eachMember.nickname, nickname);
            if (exists) {
                return true;
            }
        }
        return false;
    }
}
