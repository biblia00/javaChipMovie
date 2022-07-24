package view;

import java.util.Scanner;

import dao.ManagerDAO;
import dto.ManagerDTO;

public class ManageTheater {
	public ManageTheater() {
		Scanner sc = new Scanner(System.in);
		
		String add_theater = "";
		int up_theater = 0;
		String in_theater = "";
		int del_theater = 0;
		
		ManagerDAO mdao = new ManagerDAO();
		
		
		while(true) {
			System.out.println("���������������������������������������������� ����������������������������������������������");
			System.out.println("���� ���� �������Դϴ�.\n" +"1. ���� ����ϱ�\n" + "2. ���� �����ϱ�\n" + "3. ���� �����ϱ�\n" + "4. ������");
			int choice = sc.nextInt();
			
			if (choice == 1) {
				System.out.println("�������������������������������� ��ϡ���������������������������");
				System.out.println(mdao.theater());
				System.out.print("���ο� ������ ���� �ּ��� : ");
				add_theater = sc.next();
				if(mdao.check_theater(add_theater)) {
					ManagerDTO site = new ManagerDTO(add_theater);
				if(mdao.add_theater(site)) {
					System.out.println("������ �߰� �Ǿ����ϴ�.");
				}else {
					System.out.println("���� �߰��� ���� �ϼ̽��ϴ�.");
				}
					
			}else {
				System.out.println("�ߺ��Ǵ� ������ �ֽ��ϴ�.");
			}
				
			}else if (choice == 2){
				System.out.println("�������������������������������� ��������������������������������");
				System.out.println(mdao.theater());
				System.out.print("���� �� ������ ���� �ٶ��ϴ� : ");
				up_theater = sc.nextInt();
				System.out.print("����� ������ �����ּ���  : ");
				in_theater = sc.next();
				if(mdao.change_theater(up_theater,in_theater)) {
					System.out.println("���� ������ �Ϸ� �Ǿ����ϴ�.");
				}else {
					System.out.println("���� ������ ���� �ϼ̽��ϴ�.");
				}

				
			}else if (choice == 3) {
				System.out.println("�������������������������������� ��������������������������������");
				System.out.println(mdao.theater());
				System.out.print("���� �� ������ ���� �ٶ��ϴ� : ");
				del_theater = sc.nextInt();
				if(mdao.del_theater(del_theater)) {
					System.out.println("������ ���� �Ǿ����ϴ�.");
				}else {
					System.out.println("���� ������ ���� �ϼ̽��ϴ�.");
				}
				
			}else {
				System.out.println("�ȳ��� ������ ~~");
				new ManageView();
			}
			
			
			
			
				
		}
	}

}

