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
				System.out.println("������������������������(" + loginUser.getUserid() + ")�� ������䡪����������������������");
				System.out.println("�̸� : " + loginUser.getUsername());
				System.out.println("���� : " + loginUser.getBirth());
				System.out.println("��ȭ��ȣ : " + loginUser.getUserphone());
				System.out.println("�ּ� : " + loginUser.getUseraddr());
				System.out.println("�̸��� : " + loginUser.getUsermail());
				System.out.println("����Ʈ : " + loginUser.getUserpoint());
				System.out.println("��� : " + loginUser.getUserlevel());
				System.out.println("��������������������������������������������������������������������������������");
				System.out.println(
						"1.��й�ȣ ���� \n2.�ڵ��� ��ȣ ���� \n3.�̸��� ���� \n4.�ּ� ���� \n5.�� ����Ʈ \n6.���ų���(��ȭ) \n7.�ֹ�����(����) \n8.���ư��� \n9.ȸ��Ż��");
				int choice = sc.nextInt();
				if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
					System.out.print("���ο� ���� : ");
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
						System.out.println("���� ���� �Ϸ�!");
					} else {
						System.out.println("���� ���� ���� / ������ �ٽ� �õ����ּ���.");
					}
				} else if (choice == 5) {
					System.out.println("������Ʈ :" + loginUser.getUserpoint() + "��");
					System.out.println("1. ����Ʈ ���� \n2. ����Ʈ ȯ�� ");
					int point = sc.nextInt();
					if (point == 1) {
						System.out.print("������ �ݾ��� �Է����ּ��� : ");
						int charge_point = sc.nextInt();
						udao.charge(charge_point);
						System.out.println("�����Ͻ� �ݾ���" + charge_point + "�Դϴ�");
						System.out.println("������ �ݾ��� " + loginUser.getUserpoint() + "�� �Դϴ�");
					} else if (point == 2) {
						System.out.println("ȯ���� �ݾ��� �Է����ּ��� : ");
						int refund_point = sc.nextInt();
						if (udao.refund(refund_point)) {
							System.out.println("ȯ���Ͻ� �ݾ���" + refund_point + "�Դϴ�");
							System.out.println("ȯ���� �ݾ��� " + loginUser.getUserpoint() + "�� �Դϴ�");
						} else {
							System.out.println("ȯ���� �ݾ׺��� ����Ʈ�� �����ϴ� / �ٽ� �õ����ּ���");
						}
					} else {
						System.out.println("���⿡�� �������ּ���");
					}
				} else if (choice == 6) {
					System.out.println("������������������������������ȭ ���� ��������������������������������");
					System.out.println(udao.reserve_movie());
					System.out.println("�������:1 / ���ư���:2");
					int movie_cancel = sc.nextInt();
					if (movie_cancel == 1) {
						System.out.print("����Ͻ� ��ȭ ���Ź�ȣ�� �Է����ּ��� : ");
						int check_movie_no = sc.nextInt();
						if (udao.movie_cancel(check_movie_no)) {
							if (udao.refund_movie(check_movie_no)) {
								int count = udao.count_reserve();
								if(count < 10) {
									if(udao.level_change(count)) {
									System.out.println("���� Ƚ�� �̴޷� ���� ����� �϶��Ǿ����ϴ� / ������ : �Ϲ�");
									}
								} else if(count >= 10 && count <30) {
									if(udao.level_change(count)) {
									System.out.println("���� Ƚ�� �̴޷����� ����� �϶��Ǿ����ϴ� / ������ : ���");
									}
								}
								System.out.println("�����ϼ̴� ��ȭ�� ��ҵǾ����ϴ�.");
								ArrayList<String> decrease = mdao.check_movie(check_movie_no);
								mdao.minusViewer(decrease.get(0), Integer.parseInt(decrease.get(1)));
							}
						}
					} else if (movie_cancel == 2) {
						continue;
					} else {
						System.out.println("�߸� �Է��ϼ̽��ϴ� / �ش�Ǵ� ��ȣ�� �Է����ּ���");
					}
				} else if (choice == 7) {
					System.out.println("�������������������������������� �ֹ� ��������������������������������");
					System.out.println(udao.reserve_food());
					System.out.println("�ֹ����:1 / ���ư���:2");
					int food_cancel = sc.nextInt();
					if (food_cancel == 1) {
						System.out.println("����Ͻ� ���� ���Ź�ȣ�� �Է����ּ��� : ");
						int check_food_no = sc.nextInt();
						if (udao.food_cancel(check_food_no)) {
							if (udao.refund_food(check_food_no)) {
								System.out.println("�ֹ��ϼ̴� ������ ��ҵǾ����ϴ�.");
							}
						}
					} else if (food_cancel == 2) {
						continue;
					} else {
						System.out.println("�߸� �Է��ϼ̽��ϴ� / �ش�Ǵ� ��ȣ�� �Է����ּ���");
					}
				} else if (choice == 8) {
					System.out.print("���� ȭ������ ���ư��ϴ�");
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
					System.out.print("��й�ȣ ���Է� : ");
					String userpw = sc.next();
					if (loginUser.getUserpw().equals(userpw)) {
						if (udao.leaveId(loginUser.getUserid(), loginUser.getUserpw())) {
							System.out.println("�׵��� �̿����ּż� �����մϴ�.");
							Session.put("loginUser", null);
						} else {
							System.out.println("Ż����� /�ٽýõ����ּ���.");
						}
					} else {
						System.out.println("��й�ȣ ���� / �ٽ� �õ����ּ���.");
					}
				}
			}
		}
	}
}
