package view;

import java.util.Scanner;

public class ManageView {
	public ManageView() {
		System.out.print("1. ��ȭ ���� \n2. ���� ����\n3. �󿵽ð�ǥ ����\n4. Ǫ����Ʈ ����\n5. �α׾ƿ�\n=> ");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();

		while (true) {

			if (choice == 1) {
				new ManageMovie();
			} else if (choice == 2) {
				new ManageTheater();
			} else if (choice == 3) {
				new ManageSchedule();
			} else if (choice == 4) {
				new ManageFood();
			} else if (choice == 5) {
				System.out.print("�α׾ƿ� �Ͻðڽ��ϱ�?\n1. ��\n2. �ƴϿ�\n=> ");
				int select = sc.nextInt();
				if (select == 1) {
					System.out.println("�ȳ��ϰ�����.");
					break;
				} else if (select == 2) {
					return;
				}
			}
		}
	}
}
