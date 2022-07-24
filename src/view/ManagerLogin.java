package view;

import java.util.Scanner;

import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class ManagerLogin {

	public ManagerLogin() {
		System.out.println("������������������������������LOGIN������������������������������");
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		System.out.print("���̵� : ");
		String userid = sc.next();
		System.out.print("��й�ȣ : ");
		String userpw = sc.next();
		if (udao.login(userid, userpw)) {
			UserDTO loginUser = (UserDTO) Session.get("loginUser");
			System.out.println("������ " + loginUser.getUsername() + "�� �������~");
			new ManageView();
		} else {
			System.out.println("�α��� ����");
		}
	}

}
