package view;

import java.util.Scanner;

import dao.MovieDAO;

public class MovieView {
	public MovieView() {
		Scanner sc = new Scanner(System.in);
		MovieDAO mdao = new MovieDAO();

		while (true) {
			System.out.println("�������������");
			System.out.println("1.��ü��ȭ ���� \n2.�����ο�ȭ \n3.����������ȭ \n4.�����Ϸ����� \n5.������");
			System.out.println("�������������");
			System.out.print("��ȣ�� �Է����ּ��� : ");
			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("��������������������������ü ��ȭ����������������������");
				System.out.println(mdao.movie_all());
				System.out.print("���� �� ��ȭ ��ȣ �Է� : ");
				int choice_movie = sc.nextInt();
				System.out.println("��������������������������������������������������������������������������������");
				System.out.println(mdao.detail_more(choice_movie));
				System.out.println("��������������������������������������������������������������������������������");
			} else if (choice == 2) {
				System.out.println("������������������������������ ��ȭ����������������������");
				System.out.println(mdao.movie());
				System.out.print("���� �� ��ȭ ��ȣ �Է� : ");
				int choice_movie = sc.nextInt();
				System.out.println("��������������������������������������������������������������������������������");
				System.out.println(mdao.detail_more(choice_movie));
			} else if (choice == 3) {
				System.out.println("�������������������������������� ��ȭ����������������������");
				System.out.println(mdao.upcoming());
				System.out.println("������������������������������������������������������������������");
				System.out.print("���� �� ��ȭ ��ȣ �Է� : ");
				int choice_movie = sc.nextInt();
				System.out.println(mdao.detail_more(choice_movie));
			} else if (choice == 4) {
				new Reservation();
			} else if (choice == 5) {
				System.out.print("���� ȭ������ �̵��մϴ�");
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
				System.out.println("���⿡�� �������ּ���");
			}
		}
	}
}
