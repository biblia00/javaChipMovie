package view;

import java.util.Scanner;

import dao.FoodDAO;
import dto.FoodDTO;

public class ManageFood {
	public ManageFood() {
		Scanner sc = new Scanner(System.in);
		System.out.print("����������������������������FOOD MENU MANAGE����������������������������\n"
				+ "1. ���\n2. ����\n3. ����\n4. ������\n=> ");
		FoodDAO fdao = new FoodDAO();
		int choice = sc.nextInt();
		int food_no = 0;
		
		if(choice == 1) {	//���� ���
			System.out.print("���ĸ� : ");
			String food_name = sc.next();
			
			System.out.print("���� : ");
			int food_price = sc.nextInt();
			
			FoodDTO newfood = new FoodDTO(food_no, food_name, food_price);
			
			fdao.insertFood(newfood);
			System.out.println("��� �Ϸ�!");
			
		} else if(choice == 2) {	//���� ����
			System.out.print(fdao.foodList());
			
			System.out.print("������ ���� ��ȣ : ");
			int select = sc.nextInt();
			
			System.out.print("�̸� : ");
			String name = sc.next();
			
			System.out.print("�ݾ� : ");
			int price = sc.nextInt();
			
			fdao.updateFood(select, name, price);
			System.out.println("���� �Ϸ�!");
			
		} else if(choice == 3) {	//���� ����
			System.out.print(fdao.foodList());
			
			System.out.print("������ ���� ��ȣ : ");
			int select1 = sc.nextInt();
			
			fdao.deleteFood(select1);
			System.out.println("���� �Ϸ�!");
			
		} else if(choice == 4) {
			new ManageView();
		}
		
	}
}
