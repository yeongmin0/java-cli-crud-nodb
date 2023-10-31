package com.example.demo.ui;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;

import java.util.List;
import java.util.Scanner;

public class MainUi {

    private Path path;
    private final Scanner scanner;
    private final MemberService memberService;

    private Member loginMember; // <<< 로그인 시 사용.

    public MainUi() {
        this.path = Path.HOME;
        this.scanner = new Scanner(System.in);
        this.memberService = new MemberService();
    }

    public void run() {
        // 반복문에 Label 달면 -> break, continue 할 때 중첩 반복문 중에서 어느 반복문을 break/continue 할지 선택 가능.
        mainLoop: while (true) {
            switch (path) {
                case HOME -> home();
                case SIGN_UP -> signUp();
                case SIGN_IN -> {
                }
                case SIGN_OUT -> {
                }
                case PRINT_ALL_MEMBERS -> memberList();
                case PRINT_LOGIN_MEMBER -> whoAmI();
                case QUIT -> {
                    break mainLoop;
                }
            }
        }

        System.out.println("Good bye! See ya!");
    }

    void home() {
        int sel;
        System.out.print("""
                 !! Welcome !!
                                
                1. 회원가입
                2. 로그인
                3. 회원리스트
                4. 접속중인 리스트
                0. Quit
                
                >\s""");
        sel = scanner.nextInt();
        scanner.nextLine(); // flush buff

        path = switch (sel) {
            case 1 -> Path.SIGN_UP;
            case 2 -> Path.SIGN_IN;
            case 3 -> Path.PRINT_ALL_MEMBERS;
            case 4 -> Path.PRINT_LOGIN_MEMBER;
            case 0 -> Path.QUIT;
            default -> {
                System.out.println("Choose one of above numbers.");
                yield  Path.HOME; // switch문의 반환값 같은 역할.
            }
        };
    }

    void signUp() {
        String username;
        String password;
        String nickname;

        System.out.println(" !! Register Your Account !!");

        System.out.println("Username");
        System.out.print("> ");
        username = scanner.nextLine().strip();

        System.out.println("password");
        System.out.print("> ");
        password = scanner.nextLine().strip();

        System.out.println("nickname");
        System.out.print("> ");
        nickname = scanner.nextLine().strip();

        // TODO register account with member service.

        path = Path.HOME;
    }

    void memberList() {
        List<Member> members = memberService.getAllMembers();

        System.out.println(" !! Member List !!\n");

        for (var member : members) {
            System.out.println(member); // toString() 됨.
        }
        System.out.println();

        path = Path.HOME; // <<< Path 변경 안 하면 무한루프 발생.
    }

    void whoAmI() {
        if (loginMember == null) {
            System.out.println("Nobody logged in.");
        } else {
            System.out.println(loginMember);
        }

        path = Path.HOME;
    }

    // 회원내에 같은 닉네임/풀네임이 있는지 판별하고 알려주는 기능.
    private boolean serverByUsername(String username) {
        boolean serverExists = memberService.serverUsername(username);

        if (serverExists) {
            System.out.println("이미 닉네임이 존재합니다.");
        }
        return serverExists;


    }

    private boolean serverBynickname(String nickname) {
        boolean serverExists = memberService.serverNickname(nickname);

        if (serverExists) {
            System.out.println("이미 풀네임이 존재합니다.");
        }
        return serverExists;
    }

    // [1] 서버안에서 회원가입을 할시.
    // [2] 같은 닉네임(완료) , 풀네임 ,이 있을시 true / false 가 나오도록 할 것이다.
}
