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


}
