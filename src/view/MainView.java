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
				System.out.println("1.영화정보 \n2.예매하기 \n3.푸드코트 \n4.마이페이지 \n5.이벤트보기 \n6.로그아웃");
				int choice = 0;
				try {
					sc = new Scanner(System.in);
					choice = sc.nextInt();
				} catch (InputMismatchException ime) {
					System.out.println("잘못 입력하셨습니다 / 보기에 있는 번호중 하나를 선택해주세요");
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
					System.out.println("이용해주셔서 감사합니다");
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
			System.out.println("세션이 만료되었습니다 / 로그인 후 이용하세요");
		}
	}

}