package com.example.demo.service;

import com.example.demo.domain.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private final List<Member> memberList;
    private final MemberIdSequenceManager sequenceManager;

    public MemberService() {
        this.memberList = new ArrayList<>();
        this.sequenceManager = new MemberIdSequenceManager();
    }

    public Member signUp(String username, String password, String nickname) {
        long id = sequenceManager.increaseAndGetId();
        // TODO register the account.
        return null;
    }

    public List<Member> getAllMembers() {
        // 변경할 수 없는 사본을 제공.
        return List.copyOf(memberList);
    }
}
