package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class MypageView {
	public MypageView() {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		MovieDAO mdao = new MovieDAO();
		if (Session.get("loginUser") != null) {
			while (true) {
				System.out.println("――――――――――――(" + loginUser.getUserid() + ")님 어서오세요――――――――――――");
				System.out.println("이름 : " + loginUser.getUsername());
				System.out.println("나이 : " + loginUser.getBirth());
				System.out.println("전화번호 : " + loginUser.getUserphone());
				System.out.println("주소 : " + loginUser.getUseraddr());
				System.out.println("이메일 : " + loginUser.getUsermail());
				System.out.println("포인트 : " + loginUser.getUserpoint());
				System.out.println("등급 : " + loginUser.getUserlevel());
				System.out.println("――――――――――――――――――――――――――――――――――――――――");
				System.out.println(
						"1.비밀번호 수정 \n2.핸드폰 번호 수정 \n3.이메일 수정 \n4.주소 수정 \n5.내 포인트 \n6.예매내역(영화) \n7.주문내역(음식) \n8.돌아가기 \n9.회원탈퇴");
				int choice = sc.nextInt();
				if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
					System.out.print("새로운 정보 : ");
					sc = new Scanner(System.in);
					String newData = sc.nextLine();
					boolean check = false;
					switch (choice) {
					case 1:
						check = udao.modifyUser(loginUser.getUserid(), 1, udao.encrypt(newData));
						break;
					case 2:
					case 3:
					case 4:
						check = udao.modifyUser(loginUser.getUserid(), choice + 5, newData);
						break;
					}
					if (check) {
						System.out.println("정보 수정 완료!");
					} else {
						System.out.println("정보 수정 실패 / 다음에 다시 시도해주세요.");
					}
				} else if (choice == 5) {
					System.out.println("내포인트 :" + loginUser.getUserpoint() + "원");
					System.out.println("1. 포인트 충전 \n2. 포인트 환불 ");
					int point = sc.nextInt();
					if (point == 1) {
						System.out.print("충전할 금액을 입력해주세요 : ");
						int charge_point = sc.nextInt();
						udao.charge(charge_point);
						System.out.println("충전하신 금액은" + charge_point + "입니다");
						System.out.println("충전후 금액은 " + loginUser.getUserpoint() + "원 입니다");
					} else if (point == 2) {
						System.out.println("환불할 금액을 입력해주세요 : ");
						int refund_point = sc.nextInt();
						if (udao.refund(refund_point)) {
							System.out.println("환불하신 금액은" + refund_point + "입니다");
							System.out.println("환불후 금액은 " + loginUser.getUserpoint() + "원 입니다");
						} else {
							System.out.println("환불할 금액보다 포인트가 적습니다 / 다시 시도해주세요");
						}
					} else {
						System.out.println("보기에서 선택해주세요");
					}
				} else if (choice == 6) {
					System.out.println("──────────────영화 예매 내역──────────────");
					System.out.println(udao.reserve_movie());
					System.out.println("예매취소:1 / 돌아가기:2");
					int movie_cancel = sc.nextInt();
					if (movie_cancel == 1) {
						System.out.print("취소하실 영화 예매번호를 입력해주세요 : ");
						int check_movie_no = sc.nextInt();
						if (udao.movie_cancel(check_movie_no)) {
							if (udao.refund_movie(check_movie_no)) {
								int count = udao.count_reserve();
								if(count < 10) {
									if(udao.level_change(count)) {
									System.out.println("예매 횟수 미달로 인한 등급이 하락되었습니다 / 현재등급 : 일반");
									}
								} else if(count >= 10 && count <30) {
									if(udao.level_change(count)) {
									System.out.println("예매 횟수 미달로인한 등급이 하락되었습니다 / 현재등급 : 골드");
									}
								}
								System.out.println("예매하셨던 영화가 취소되었습니다.");
								ArrayList<String> decrease = mdao.check_movie(check_movie_no);
								mdao.minusViewer(decrease.get(0), Integer.parseInt(decrease.get(1)));
							}
						}
					} else if (movie_cancel == 2) {
						continue;
					} else {
						System.out.println("잘못 입력하셨습니다 / 해당되는 번호를 입력해주세요");
					}
				} else if (choice == 7) {
					System.out.println("──────────────음식 주문 내역──────────────");
					System.out.println(udao.reserve_food());
					System.out.println("주문취소:1 / 돌아가기:2");
					int food_cancel = sc.nextInt();
					if (food_cancel == 1) {
						System.out.println("취소하실 음식 예매번호를 입력해주세요 : ");
						int check_food_no = sc.nextInt();
						if (udao.food_cancel(check_food_no)) {
							if (udao.refund_food(check_food_no)) {
								System.out.println("주문하셨던 음식이 취소되었습니다.");
							}
						}
					} else if (food_cancel == 2) {
						continue;
					} else {
						System.out.println("잘못 입력하셨습니다 / 해당되는 번호를 입력해주세요");
					}
				} else if (choice == 8) {
					System.out.print("메인 화면으로 돌아갑니다");
					for (int i = 0; i < 3; i++) {
						System.out.print(".");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
						}
					}
					System.out.println();
					new MainView();
				} else if (choice == 9) {
					System.out.print("비밀번호 재입력 : ");
					String userpw = sc.next();
					if (loginUser.getUserpw().equals(userpw)) {
						if (udao.leaveId(loginUser.getUserid(), loginUser.getUserpw())) {
							System.out.println("그동안 이용해주셔서 감사합니다.");
							Session.put("loginUser", null);
						} else {
							System.out.println("탈퇴실패 /다시시도해주세요.");
						}
					} else {
						System.out.println("비밀번호 오류 / 다시 시도해주세요.");
					}
				}
			}
		}
	}
}
