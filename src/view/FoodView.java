package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dao.FoodDAO;
import dao.Session;
import dto.FoodDTO;
import dto.UserDTO;

public class FoodView {
	public FoodView() {
		UserDTO loginUser = (UserDTO)Session.get("loginUser");
		if (Session.get("loginUser") != null) {

			Scanner sc = new Scanner(System.in);
			FoodDAO fdao = new FoodDAO();

			System.out.print("1. �ֹ��ϱ�\n2. ������\n=> ");
			int price_sum = 0;
			int choice = sc.nextInt();
			HashMap<String, Integer>foods = new HashMap<String, Integer>();
					  
			//��ǰ����
			if (choice == 1) {
				int select = 0;
				boolean flag = true;
				System.out.println("�ֹ��Ͻ� ��ǰ ��ȣ �ϳ��� �Է����ּ���.");
				while(flag) {
					System.out.print(fdao.foodList() + "=> ");
					select = sc.nextInt();
					if(select == 0) {
						break;
					} else {
						
						ArrayList<String> food = fdao.foodOrder(select);
						price_sum += Integer.parseInt(food.get(0));
						String foodname = food.get(1);
						
						if(foods.containsKey(foodname)){
							foods.replace(foodname, foods.get(foodname) + 1);
						} else {
							foods.put(foodname, 1);
						}
						System.out.println("�߰� ������ ��ǰ ��ȣ & ������ 0���� �����ּ���.");
					}
				}
				
				while(true) {
					//����
					if(select== 0) {
						flag = false;
						System.out.println(price_sum + "�� �����ϱ�");
						System.out.print("���� ����� �������ּ���.\n1.����Ʈ ����\n2.���� ����\n3.������\n=> ");
						int select2 = sc.nextInt();
						
						//����Ʈ ����
						if(select2 == 1) {
							if(price_sum <= loginUser.getUserpoint()) {
								fdao.pay(price_sum);
								loginUser = (UserDTO)Session.get("loginUser");
								System.out.println("���� ����");
								System.out.println("�ֹ� ���� : " + foods.entrySet());	//���� ���
								System.out.println("���� �ݾ� : " + price_sum + "��");
								FoodDTO reserve_food = new FoodDTO(foods, price_sum);
								fdao.insertOrder(reserve_food);	
								System.out.println("����Ʈ �ܾ� : " + loginUser.getUserpoint() + "��");
								System.out.print("�����Ͻðڽ��ϱ�?\n1.��\n2.�ƴϿ�\n=> ");
								int select3 = sc.nextInt();
								
								//����Ʈ ����
								if(select3 == 1) {
									fdao.save(price_sum);
									System.out.println("�����Ǿ����ϴ�. �̿����ּż� �����մϴ�."
											+ "\n����Ʈ �ܾ� : " + loginUser.getUserpoint() + "��");
									break;
								} else if(select3 == 2) {
									System.out.println("�̿����ּż� �����մϴ�.");
									break;
								}
							} else {
								System.out.println("�ܾ��� �����մϴ�.");
							}
							
						//���� ����
						} else if(select2 == 2) {
							System.out.println("fdao.coupon();");
							//fdao.coupon();
						} else if(select2 == 3) {
							System.out.println("�̿����ּż� �����մϴ�.");
							break;
						} else {
							System.out.println("�߸� �Է��ϼ̽��ϴ�.");
						}
					}
				}
			} else if (choice == 2) {
				System.out.println("����ȭ������ �̵��մϴ�.");
				MainView main = new MainView();
			} else {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
		}
	}
}
