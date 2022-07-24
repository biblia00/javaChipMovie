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

			System.out.print("1. 주문하기\n2. 나가기\n=> ");
			int price_sum = 0;
			int choice = sc.nextInt();
			HashMap<String, Integer>foods = new HashMap<String, Integer>();
					  
			//상품선택
			if (choice == 1) {
				int select = 0;
				boolean flag = true;
				System.out.println("주문하실 상품 번호 하나를 입력해주세요.");
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
						System.out.println("추가 선택은 상품 번호 & 결제는 0번을 눌러주세요.");
					}
				}
				
				while(true) {
					//결제
					if(select== 0) {
						flag = false;
						System.out.println(price_sum + "원 결제하기");
						System.out.print("결제 방법을 선택해주세요.\n1.포인트 결제\n2.쿠폰 결제\n3.나가기\n=> ");
						int select2 = sc.nextInt();
						
						//포인트 결제
						if(select2 == 1) {
							if(price_sum <= loginUser.getUserpoint()) {
								fdao.pay(price_sum);
								loginUser = (UserDTO)Session.get("loginUser");
								System.out.println("결제 성공");
								System.out.println("주문 내역 : " + foods.entrySet());	//결제 목록
								System.out.println("결제 금액 : " + price_sum + "원");
								FoodDTO reserve_food = new FoodDTO(foods, price_sum);
								fdao.insertOrder(reserve_food);	
								System.out.println("포인트 잔액 : " + loginUser.getUserpoint() + "원");
								System.out.print("적립하시겠습니까?\n1.예\n2.아니오\n=> ");
								int select3 = sc.nextInt();
								
								//포인트 적립
								if(select3 == 1) {
									fdao.save(price_sum);
									System.out.println("적립되었습니다. 이용해주셔서 감사합니다."
											+ "\n포인트 잔액 : " + loginUser.getUserpoint() + "원");
									break;
								} else if(select3 == 2) {
									System.out.println("이용해주셔서 감사합니다.");
									break;
								}
							} else {
								System.out.println("잔액이 부족합니다.");
							}
							
						//쿠폰 결제
						} else if(select2 == 2) {
							System.out.println("fdao.coupon();");
							//fdao.coupon();
						} else if(select2 == 3) {
							System.out.println("이용해주셔서 감사합니다.");
							break;
						} else {
							System.out.println("잘못 입력하셨습니다.");
						}
					}
				}
			} else if (choice == 2) {
				System.out.println("메인화면으로 이동합니다.");
				MainView main = new MainView();
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
}
