package view;

import java.util.Scanner;

public class ManagerPage {
	public ManagerPage() {
		System.out.println("1. ������ ȸ������\n2. ������ �α���\n3. ������");
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
