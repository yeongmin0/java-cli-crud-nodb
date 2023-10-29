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
        mainLoop:
        while (true) {
            switch (path) {
                case HOME -> home();
                case SIGN_UP -> signUp();
                case SIGN_IN -> signIn();
                case SIGN_OUT -> signOut();
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
                                
                1. Sign up
                2. Login
                3. Member List
                4. Who logged in?
                5. Sign out
                0. Quit
                                
                >\s""");
        sel = scanner.nextInt();
        scanner.nextLine(); // flush buff

        path = switch (sel) {
            case 1 -> Path.SIGN_UP;
            case 2 -> Path.SIGN_IN;
            case 3 -> Path.PRINT_ALL_MEMBERS;
            case 4 -> Path.PRINT_LOGIN_MEMBER;
            case 5 -> Path.SIGN_OUT;
            case 0 -> Path.QUIT;
            default -> {
                System.out.println("Choose one of above numbers.");
                yield Path.HOME; // switch문의 반환값 같은 역할.
            }
        };
    }

    void signUp() {
        String username;
        String password;
        String nickname;

        System.out.println(" !! Register Your Account !!");

        // rarely used do~while(condition);
        do {
            System.out.println("Username");
            System.out.print("> ");
            username = scanner.nextLine().strip();
        } while (existsByUsername(username));

        System.out.println("password");
        System.out.print("> ");
        password = scanner.nextLine().strip();

        do {
            System.out.println("nickname");
            System.out.print("> ");
            nickname = scanner.nextLine().strip();
        } while (existsByNickname(nickname));

        Member savedmember = memberService.signUp(username, password, nickname);

        if (savedmember == null) {
            System.out.println("Couldn't register.");
        }

        path = Path.HOME;
    }

    void signIn() {
        String username;
        String password;

        System.out.println("Login ");

        System.out.println("ID");
        System.out.print(">");
        username = scanner.nextLine().strip();

        System.out.println("password");
        System.out.print(">");
        password = scanner.nextLine().strip();

        loginMember = memberService.signIn(username, password);

        if (loginMember == null) {
            System.out.println("Couldn't log in.");
        }

        path = Path.HOME;
    }

    void signOut() {
        loginMember = null;
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

    private boolean existsByUsername(String username) {
        boolean alreayExists = memberService.existsByUsername(username);

        if (alreayExists) {
            System.out.println("The username already exists.");
        }

        return alreayExists;
    }

    private boolean existsByNickname(String username) {
        boolean alreayExists = memberService.existsByNickname(username);

        if (alreayExists) {
            System.out.println("The nickname already exists.");
        }

        return alreayExists;
    }
}
