package view;

import java.util.Scanner;

import dao.UserDAO;
import dto.UserDTO;

public class FindIDView {
	public FindIDView() {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		String find_ID = "";

		System.out.print("이름을 입력해 주세요 : ");
		String username = sc.next();

		System.out.println("생년월일을 YYYY.MM.DD 형식으로 입력해 주세요 : ");
		String birth = sc.next();
		while (!(udao.isBirth(birth))) {
			System.out.println("형식에 맞지 않습니다. 다시 입력 해 주세요 (ex : YYYY.MM.DD) :");
			birth = sc.next();
		}

		System.out.println("이용하는 통신사를 선택 해 주세요 : " + "\n① SKT\n② KT\n③ LG U+");
		String phagency = sc.next();

		System.out.println("휴대폰 번호를 입력해 주세요 (ex:010-0000-0000) : ");
		String userphone = sc.next();
		while (!(udao.isPhone(userphone))) {
			System.out.println("형식에 맞지 않습니다. 다시 입력해주세요(ex: 010-0000-0000) :");
			userphone = sc.next();
		}

		System.out.println("회원가입시 선택한 질문을 골라주세요(숫자로 입력 해 주세요)\n"
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
		
		find_ID = udao.findID(username, birth, phagency, userphone, answer);
		
		if(find_ID != null) {
			System.out.println("["+username+"]" + "님이 찾으시는 아이디는 " + "["+find_ID+"]" + "입니다!");
		}  else {
			System.out.println("입력하신 정보가 맞지 않습니다. 다시 확인해서 진행 해 주세요 !");
		}
		
	
	}
}
