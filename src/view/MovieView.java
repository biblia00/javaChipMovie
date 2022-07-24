package view;

import java.util.Scanner;

import dao.MovieDAO;

public class MovieView {
	public MovieView() {
		Scanner sc = new Scanner(System.in);
		MovieDAO mdao = new MovieDAO();

		while (true) {
			System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼");
			System.out.println("1.전체영화 보기 \n2.상영중인영화 \n3.개봉예정영화 \n4.예매하러가기 \n5.나가기");
			System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲");
			System.out.print("번호를 입력해주세요 : ");
			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("────────────전체 영화───────────");
				System.out.println(mdao.movie_all());
				System.out.print("상세히 볼 영화 번호 입력 : ");
				int choice_movie = sc.nextInt();
				System.out.println("────────────────────────────────────────");
				System.out.println(mdao.detail_more(choice_movie));
				System.out.println("────────────────────────────────────────");
			} else if (choice == 2) {
				System.out.println("────────────상영중인 영화───────────");
				System.out.println(mdao.movie());
				System.out.print("상세히 볼 영화 번호 입력 : ");
				int choice_movie = sc.nextInt();
				System.out.println("────────────────────────────────────────");
				System.out.println(mdao.detail_more(choice_movie));
			} else if (choice == 3) {
				System.out.println("────────────개봉예정 영화───────────");
				System.out.println(mdao.upcoming());
				System.out.println("─────────────────────────────────");
				System.out.print("상세히 볼 영화 번호 입력 : ");
				int choice_movie = sc.nextInt();
				System.out.println(mdao.detail_more(choice_movie));
			} else if (choice == 4) {
				new Reservation();
			} else if (choice == 5) {
				System.out.print("메인 화면으로 이동합니다");
				for (int i = 0; i < 3; i++) {
					System.out.print(".");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
				}
				System.out.println("\n");
				MainView mv = new MainView();
			} else {
				System.out.println("보기에서 선택해주세요");
			}
		}
	}
}
