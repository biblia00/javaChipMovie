package view;

import java.util.Scanner;

import dto.UserDTO;

import java.util.Scanner;
import java.util.regex.Pattern;

import dao.UserDAO;
import dto.UserDTO;

public class JoinView {
	public JoinView() {
		System.out.println("───────────────JOIN───────────────");
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		String userpw = "";
		int userpoint = 0;
		String userlevel = "일반";

		System.out.print("ID를 입력해주세요 : ");
		String userid = sc.next();
		if (udao.checkId(userid)) {
			while (true) {
				System.out.print("PASSWORD를 입력해주세요 : ");
				userpw = sc.next();
				System.out.print("PASSWORD를 확인해주세요 : ");
				String userpw_re = sc.next();

				if (userpw.equals(userpw_re)) {
					break;
				} else {
					System.out.println("비밀번호를 다시 확인해주세요.");
				}
			}
			System.out.println("──────────────────────────────────");
			System.out.print("이름을 입력해주세요 : ");
			String username = sc.next();

			System.out.print("닉네임을 입력해주세요 : ");
			String usernkname = sc.next();
			if (udao.checkNKname(usernkname)) {

				System.out.println("생년 월일을 YYYY.MM.DD 형식으로 입력 해 주세요 : ");
				String birth = sc.next();
				while (!(udao.isBirth(birth))) {
					System.out.println("형식에 맞지 않습니다. 다시 입력 해 주세요 (ex : YYYY.MM.DD) :");
					birth = sc.next();
				}

				System.out.println("성별을 선택 해 주세요 :" + "\n① 남성\n② 여성 ");
				String gender = sc.next();
				System.out.println("──────────────────────────────────");

				System.out.println("이용하는 통신사를 선택 해 주세요 : " + "\n① SKT\n② KT\n③ LG U+");
				String phagency = sc.next();

				System.out.println("핸드폰 번호를 입력해주세요 (ex:010-0000-0000): ");
				String userphone = sc.next();
				// 전화번호 형식 검사 (010-0000-0000)
				while (!(udao.isPhone(userphone))) {
					System.out.println("형식에 맞지 않습니다. 다시 입력해주세요(ex: 010-0000-0000) :");
					userphone = sc.next();
				}

				System.out.println("──────────────────────────────────");
				System.out.print("e-mail을 입력해주세요 : ");
				String useremail = sc.next();
				// 이메일 형식 검사
				while (!(udao.isEmail(useremail))) {
					System.out.println("형식에 맞지 않습니다. 다시 입력해주세요 (ex : javachip@cinema.com) : ");
					useremail = sc.next();
				}

				System.out.print("주소를 입력해주세요 : ");
				// 그 동안 받았던 것 비워주기
				sc = new Scanner(System.in);
				String useraddr = sc.nextLine();
				System.out.println("──────────────────────────────────");

				System.out.println("아이디, 비밀번호를 찾기 위한 질문들 입니다.\n " + "아래 질문을 골라 주세요 (숫자로 입력 해 주세요)\n"
						+ "1. 가장 기억에 남는 선생님 성함은?\n" + "2. 자신의 인생 좌우명은?\n" + "3. 가장 존경하는 인물의 이름은?\n"
						+ "4. 가장 좋아하는 캐릭터는?\n" + "5. 현재 or 어릴 때 키운 반려동물 이름은?");
				int choice = sc.nextInt();
				String answer = "";
				if (choice == 1) {
					System.out.println("질문의 대한 답을 적어 주세요.\n " + "가장 기억에 남는 선생님 성함은?\n" + "");
					answer = sc.next();
				} else if (choice == 2) {
					System.out.println("질문에 대한 답을 적어주세요.\n " + "자신의 인생 좌우명은?");
					answer = sc.next();
				} else if (choice == 3) {
					System.out.println("질문에 대한 답을 적어주세요.\n" + "가장 존경하는 인물의 이름은?");
					answer = sc.next();
				} else if (choice == 4) {
					System.out.println("질문에 대한 답을 적어주세요.\n" + "가장 좋아하는 캐릭터는 ?");
					answer = sc.next();
				} else if (choice == 5) {
					System.out.println("질문에 대한 답을 적어주세요.\n" + "현재 or 어릴 때 키운 반려동물 이름은?");
					answer = sc.next();

				}

				UserDTO newUser = new UserDTO(userid, userpw, username, usernkname, birth, gender, phagency, userphone,
						useremail, useraddr, userpoint, userlevel, answer);

				if (udao.join(newUser)) {
					System.out.println(username + "님 반갑습니다~\n" + "회원가입이 정상적으로 완료되었습니다.");
				} else {
					System.out.println("회원가입 실패 / 다시 확인 해 주세요.");
				}
			} else {
				System.out.println("닉네임이 중복되었습니다. 다시 확인 해주세요 !");
			}
		} else {
			System.out.println("아이디가 중복 되었습니다. 다시 확인 해주세요 !");
		}
	}
}
