package view;

import java.util.Scanner;

public class ManagerPage {
	public ManagerPage() {
		System.out.println("1. 관리자 회원가입\n2. 관리자 로그인\n3. 나가기");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		if(choice == 1) {
			new ManagerJoin();
		} else if(choice == 2) {
			new ManagerLogin();
		} else if(choice == 3) {
			new MainView();
		}
	}
	
	
}
