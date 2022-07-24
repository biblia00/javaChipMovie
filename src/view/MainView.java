package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.Session;
import dto.UserDTO;

public class MainView {
	public MainView() {

		Session.get("loginUser");
		if (Session.get("loginUser") != null) {
			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.println("1.��ȭ���� \n2.�����ϱ� \n3.Ǫ����Ʈ \n4.���������� \n5.�̺�Ʈ���� \n6.�α׾ƿ�");
				int choice = 0;
				try {
					sc = new Scanner(System.in);
					choice = sc.nextInt();
				} catch (InputMismatchException ime) {
					System.out.println("�߸� �Է��ϼ̽��ϴ� / ���⿡ �ִ� ��ȣ�� �ϳ��� �������ּ���");
				}
				
				if (choice == 1) {
					new MovieView();
				} else if (choice == 2) {
					new Reservation();
				} else if (choice == 3) {
					new FoodView();
				} else if (choice == 4) {
					new MypageView();
					break;
				} else if (choice == 5) {
					new EventView();
				} else if (choice == 6) {
					System.out.println("�̿����ּż� �����մϴ�");
					for (int i = 0; i < 3; i++) {
						System.out.print(".");
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
						}
					}
					Session.put("loginUser", null);
					Index index = new Index();
				}
			}
		} else {
			System.out.println("������ ����Ǿ����ϴ� / �α��� �� �̿��ϼ���");
		}
	}

}