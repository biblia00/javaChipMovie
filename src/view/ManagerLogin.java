package view;

import java.util.Scanner;

import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class ManagerLogin {

	public ManagerLogin() {
		System.out.println("───────────────LOGIN───────────────");
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		System.out.print("아이디 : ");
		String userid = sc.next();
		System.out.print("비밀번호 : ");
		String userpw = sc.next();
		if (udao.login(userid, userpw)) {
			UserDTO loginUser = (UserDTO) Session.get("loginUser");
			System.out.println("관리자 " + loginUser.getUsername() + "님 어서오세요~");
			new ManageView();
		} else {
			System.out.println("로그인 실패");
		}
	}

}
