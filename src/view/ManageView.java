package view;

import java.util.Scanner;

public class ManageView {
	public ManageView() {
		System.out.print("1. 영화 관리 \n2. 지점 관리\n3. 상영시간표 관리\n4. 푸드코트 관리\n5. 로그아웃\n=> ");
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
				System.out.print("로그아웃 하시겠습니까?\n1. 예\n2. 아니오\n=> ");
				int select = sc.nextInt();
				if (select == 1) {
					System.out.println("안녕하가세요.");
					break;
				} else if (select == 2) {
					return;
				}
			}
		}
	}
}
