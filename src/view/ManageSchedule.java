package view;

import java.util.Scanner;
import dao.ManagerDAO;
import dto.ManagerDTO;

public class ManageSchedule {
	public ManageSchedule() {
		Scanner sc = new Scanner(System.in);
		int in_theater = 0;
		int in_movie = 0;
		String in_screen = "";
		String in_time = "";
		String in_end_time = "";
		int up_no = 0;
		String up_time = "";
		String up_end_time = "";
		int del_no = 0;

		ManagerDAO madao = new ManagerDAO();

		while (true) {
			System.out.println("���������������������������������󿵽ð�ǥ ������������������������������������");
			System.out.println(
					"�󿵽ð�ǥ ����â �Դϴ�.\n" + "�Ʒ��� �޴��� ���� �� �ּ���." + "\n1. �ð�ǥ ���\n2. �ð�ǥ ����\n3. �ð�ǥ ����\n4. ������");
			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.print("���������������������������������ð�ǥ ��ϡ�������������������������������");
				System.out.print(madao.theater());
				System.out.println("������������������������������������������������������������������������������");
				System.out.print("���� ����� �ð�ǥ�� ������ ��ȣ�� ���� �ּ��� : ");
				in_theater = sc.nextInt();
				System.out.println("������������������������������������������������������������������������������");
				System.out.print(madao.movie());
				System.out.println("������������������������������������������������������������������������������");
				System.out.print("���� ����� �ð�ǥ�� ��ȭ ��ȣ�� �����ּ��� : ");
				in_movie = sc.nextInt();
				sc = new Scanner(System.in);
				System.out.println("������������������������������������������������������������������������������");
				System.out.println("���� ����� �ð�ǥ�� ���� ��ũ��Ÿ���� ���� �ּ��� : ");
				in_screen = sc.nextLine();
				System.out.println("������������������������������������������������������������������������������");
				System.out.println("���� ����� �ð�ǥ�� ���۽ð��� ���� �ּ��� : ");
				in_time = sc.next();
				System.out.println("���� ����� �ð�ǥ�� ������ �ð��� ���� �ּ��� :");
				in_end_time = sc.next();
				
			} else if (choice == 2) {
				System.out.print("���������������������������������ð�ǥ ������������������������������������");
				System.out.print(madao.theater());
				System.out.println("������������������������������������������������������������������������������");
				System.out.print("������ �ð�ǥ�� ���� ������ ��ȣ�� ���� �ּ��� : ");
				int up_theater = sc.nextInt();
				System.out.println("������������������������������������������������������������������������������");
				System.out.print(madao.movie());
				System.out.println("������������������������������������������������������������������������������");
				System.out.print("������ �ð�ǥ�� ��ȭ�� ��ȣ�� �����ּ��� : ");
				int up_movie = sc.nextInt();
				System.out.println("������������������������������������������������������������������������������");
				System.out.print(madao.schedule(up_theater, up_movie));
				System.out.println("������������������������������������������������������������������������������");
				System.out.print("������ �ð�ǥ�� ���� ��ȣ�� ���� �ּ���  : ");
				up_no = sc.nextInt();
				System.out.println("������������������������������������������������������������������������������");
				System.out.println("������ �ð�ǥ�� ���۽ð��� ���� �ּ��� :");
				up_time = sc.next();
				System.out.println("������ �ð�ǥ�� �����½ð��� �����ּ��� :");
				up_end_time = sc.next();
				
			} else if (choice == 3) {
				System.out.print("���������������������������������ð�ǥ ������������������������������������");
				System.out.print(madao.theater());
				System.out.println("������������������������������������������������������������������������������");
				System.out.println("������ �ð�ǥ�� ���� ������ ��ȣ�� ���� �ּ��� : ");
				int del_theater = sc.nextInt();
				System.out.println("������������������������������������������������������������������������������");
				System.out.print(madao.movie());
				System.out.println("������������������������������������������������������������������������������");
				System.out.print("������ �ð�ǥ�� ��ȭ�� ��ȣ�� ���� �ּ��� : ");
				int del_movie = sc.nextInt();
				System.out.println("������������������������������������������������������������������������������");
				System.out.print(madao.schedule(del_theater, del_movie));
				System.out.println("������������������������������������������������������������������������������");
				System.out.print("������ �ð�ǥ�� ������ȣ�� ���� �ּ��� : ");
				del_no = sc.nextInt();
			} else {
				System.out.println("�ȳ��� ���ʼ�~");
				break;
			}
			ManagerDTO schedule = new ManagerDTO(in_theater, in_movie, in_screen, in_time, in_end_time, up_no, up_time, up_end_time, del_no);
			
			if(madao.ins_time(schedule)) {
				System.out.println("�ð�ǥ �߰� �Ǿ����ϴ�.");
			} else if (madao.up_time(schedule)) {
				System.out.println("�ð�ǥ ���� �Ǿ����ϴ�.");
			} else if (madao.del_time(schedule)) {
				System.out.println("�ð�ǥ ���� �Ǿ����ϴ�.");
			} else {
				System.out.println("�ð�ǥ ������ �����߽��ϴ�. �ٽ� Ȯ���� �ּ���.");
			}
		}
	}

}
