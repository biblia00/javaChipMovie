package view;

import java.util.Scanner;

import dao.FoodDAO;
import dto.FoodDTO;

public class ManageFood {
	public ManageFood() {
		Scanner sc = new Scanner(System.in);
		System.out.print("──────────────FOOD MENU MANAGE──────────────\n"
				+ "1. 등록\n2. 수정\n3. 삭제\n4. 나가기\n=> ");
		FoodDAO fdao = new FoodDAO();
		int choice = sc.nextInt();
		int food_no = 0;
		
		if(choice == 1) {	//음식 등록
			System.out.print("음식명 : ");
			String food_name = sc.next();
			
			System.out.print("가격 : ");
			int food_price = sc.nextInt();
			
			FoodDTO newfood = new FoodDTO(food_no, food_name, food_price);
			
			fdao.insertFood(newfood);
			System.out.println("등록 완료!");
			
		} else if(choice == 2) {	//음식 수정
			System.out.print(fdao.foodList());
			
			System.out.print("수정할 음식 번호 : ");
			int select = sc.nextInt();
			
			System.out.print("이름 : ");
			String name = sc.next();
			
			System.out.print("금액 : ");
			int price = sc.nextInt();
			
			fdao.updateFood(select, name, price);
			System.out.println("수정 완료!");
			
		} else if(choice == 3) {	//음식 삭제
			System.out.print(fdao.foodList());
			
			System.out.print("삭제할 음식 번호 : ");
			int select1 = sc.nextInt();
			
			fdao.deleteFood(select1);
			System.out.println("삭제 완료!");
			
		} else if(choice == 4) {
			new ManageView();
		}
		
	}
}
