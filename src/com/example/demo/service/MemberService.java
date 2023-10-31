package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.types.MemberStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberService {
    private List<Member> memberRepository;
    private final MemberIdSequenceManager sequenceManager;

    public MemberService() {
        this.memberRepository = new ArrayList<>();
        this.sequenceManager = new MemberIdSequenceManager();
    }

    public Member signUp(String username, String password, String nickname) {


        if (serverUsername(username) || serverNickname(nickname))
            return null;


        long id = sequenceManager.increaseAndGetId();
        Instant now = Instant.now();

        {
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
    }

    // 로그인 (10 -31 구현)
    public Member singIn(String username, String password) {

        for (var eachMember : memberRepository) {
            if (Objects.equals(eachMember.username, username)
                    && (Objects.equals(eachMember.password, password))
            ) {
                ;
                // equals 같은 경우에는 같은 객체끼리 비교를 한다는 점 참고.

                return eachMember;
            }
        }
        return null;
    }

    public List<Member> getAllMembers() {
        // 변경할 수 없는 사본을 제공.
        return List.copyOf(memberRepository);
    }


    // 회원가입 기능
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

    // 로그인을 할 시 멤버 리스트에 있는 아이디 와 비밀번호가 같은지 조회하는 것 (?)
//    public boolean serverPassword(String password) {
//        for (var eachMember : memberRepository) {
//            boolean exists = Objects.equals(eachMember.username, password);
//            if (exists) {
//                return true;
//            }
//        }
//        return false;
//    }

}
