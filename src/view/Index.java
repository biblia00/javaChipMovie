package view;

import java.util.Scanner;

//자바칩 무비 첫 시작화면
public class Index {
    public static void main(String[] args) {
        System.out.println("환영합니다~ 자바칩 영화관입니다~^0^");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Join\n2. Login\n3. Find-ID\n4. Find-PASSWORD\n5. Exit\n0. Manager");
            int choice = sc.nextInt();

            if (choice == 1) {
                // 회원가입
                new JoinView();
            } else if (choice == 2) {
                // 로그인
                new LoginView();
            } else if (choice == 3) {
                // 아이디 찾기
                new FindIDView();
            } else if (choice == 4) {
                // 비밀번호 찾기
                new FindPWView();
            } else if (choice == 5) {
                // 나가기
                System.out.println("찾아주셔서 감사합니다 안녕히 가세요 ^0^");
                break;
            } else if (choice == 0) {
            	new ManagerPage();
            }
        }
    }

}
