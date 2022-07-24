package view;

import java.util.Scanner;

import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class LoginView {

	public LoginView() {
		System.out.println("───────────────LOGIN───────────────");
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		System.out.print("아이디 : ");
		String userid = sc.next();
		System.out.print("비밀번호 : ");
		String userpw = sc.next();
		if (udao.login(userid, userpw)) {
			UserDTO loginUser = (UserDTO)Session.get("loginUser");
			System.out.println(loginUser.getUsername() + "님 어서오세요~");
			new MainView();
		} else {
			System.out.println("로그인 실패");
		}
	}

}
