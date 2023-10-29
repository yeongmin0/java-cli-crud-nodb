package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.types.MemberStatus;

import java.time.Instant;
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
        // Preconditions
        if (existsByUsername(username) || existsByNickname(nickname)) {
            // TODO 예외 처리
            return null; // 함수 종료
        }

        long id = sequenceManager.increaseAndGetId();
        Instant now = Instant.now();

        Member newMember = new Member(
                id,
                username,
                password,
                nickname,
                MemberStatus.ACTIVE,
                now,
                now
        );

        memberRepository.add(newMember);
        return newMember;
    }

    public List<Member> getAllMembers() {
        // 변경할 수 없는 사본을 제공.
        return List.copyOf(memberRepository);
    }

    public boolean existsByUsername(String username) { // Positive naming for boolean variables or methods.
        for (var eachMember : memberRepository) { // 향상된 for문, var(타입 추론)
            boolean exists = Objects.equals(eachMember.username, username); // NPE
            if (exists) {
                return true;
            }
        }

        return false;
    }

    public boolean existsByNickname(String nickname) {
        for (var eachMember : memberRepository) {
            boolean exists = Objects.equals(eachMember.nickname, nickname);
            if (exists) {
                return true;
            }
        }

        return false;
    }

    public Member signIn(String username, String password) {
        // 회원 목록 중에서 username, password 모두 일치하는 회원이 있으면 그 회원을 반환한다.
        // 없으면 null을 반환한다.
        for (var eachMember : memberRepository) {
            if (
                    Objects.equals(eachMember.username, username) // Protects NPE(NullPointerException)
                    && Objects.equals(eachMember.password, password)
            ) {
                return eachMember;
            }
        }

        return null;
    }
}
