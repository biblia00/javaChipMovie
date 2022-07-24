package view;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;
import dao.UserDAO;
import dto.ReservationDTO;
import dto.UserDTO;

public class Reservation {
	public Reservation() {
		Scanner sc = new Scanner(System.in);
		MovieDAO mdao = new MovieDAO();
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		UserDAO udao = new UserDAO();

		if (Session.get("loginUser") != null) {
			// ���� ���� / ���� ����
			int choice_theater = 0;
			String theater_name = "";
			while (true) {
				System.out.println("���������������������������� ����������������������������");
				System.out.println(mdao.theater());

				System.out.print("���� ���� : ");
				try {
					sc = new Scanner(System.in);
					choice_theater = sc.nextInt();
				} catch (Exception e) {
					System.out.println("���⿡�� �Է����ּ���");
					continue;
				}
				theater_name = mdao.search_theater(choice_theater);
				if (choice_theater <= 10) {
					System.out.println("<" + theater_name + ">" + "������ �����ϼ̽��ϴ�");
					break;
				} else {
					System.out.println("���⿡�� �Է����ּ���");
				}
			}

			// ��ȭ ���� / ��ȭ ����
			int choice_movie = 0;
			String movie_name = null;
			while (true) {
				System.out.println("���������������������������� ��ȭ����������������������");
				System.out.println(mdao.movie());
				System.out.println("��������������������������������������������������������������");
				System.out.print("��ȭ ���� : ");
				try {
					sc = new Scanner(System.in);
					choice_movie = sc.nextInt();
				} catch (Exception e) {
					System.out.println("���⿡�� �Է����ּ���");
					continue;
				}
				if (choice_movie <= 6) {
					System.out.println(mdao.detail(choice_movie));
					System.out.println("��������������������������������������������������������������������������������");
					System.out.print("�����Ͻ� ��ȭ�� �����Ͻðڽ��ϱ�? 1.�������� 2.���ư���--->");
					int choice_reserve = 0;
					try {
						choice_reserve = sc.nextInt();
					} catch (Exception e) {
						System.out.println("���⿡�� �Է����ּ���");
						continue;
					}
					if (choice_reserve == 1) {
						movie_name = mdao.search_movie(choice_movie);
						System.out.println("<" + movie_name + ">" + "�� �����ϼ̽��ϴ�");
						break;
					} else if (choice_reserve == 2) {
						continue;
					} else {
						System.out.println("���⿡�� �Է����ּ���");
					}
				}
			}

			// ��¥ �Է¹ޱ�
			System.out.println("��������������������������������������������������������������");
			String[] time4 = new String[5];
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
			
			while (true) {
				int time2 = 0;

				Date time = new Date();
				String time1 = format1.format(time);
				System.out.println("������ <" + time1 + "> �Դϴ�.");
				String time3 = time1.substring(0, 8);
				for (int i = 0; i < 5; i++) {
					time2 = Integer.parseInt(time1.substring(8, 10)) + i;
					time4[i] = (time3 + time2);
					System.out.println(i + 1 + "." + time3 + time2);
				}
				break;
			}
			System.out.print("�����Ͻ� ���� �������ּ��� :");
			int choice = sc.nextInt();
			System.out.println(time4[choice - 1] + "�� �����ϼ̽��ϴ�.");

			// ������ �󿵽ð�ǥ �����ֱ�
			String start_time;
			String end_time;
			ArrayList<String> sc_time = mdao.schedule(choice_theater, choice_movie);

			while (true) {

				System.out.println("\n<<<<<<<��ȭ �� �ð�ǥ>>>>>>>");

				for (int i = 1; i < sc_time.size(); i++) {
					System.out.println(i + "." + sc_time.get(0) + "  " + sc_time.get(i));
				}

				System.out.println("�󿵽ð��� ������ �ּ���:");
				int no = 0;

				try {
					sc = new Scanner(System.in);
					no = sc.nextInt();
				} catch (Exception e) {
					System.out.println("���ڸ� �Է����ּ���");
					continue;
				}

				if (no <= sc_time.size() && no >= 1) {

					System.out.print(sc_time.get(no) + " �����Ͻ� �ð����� �����Ͻðڽ��ϱ�? 1.�������� 2.���ư���--->");

					int choice_time = 0;

					try {
						choice_time = sc.nextInt();
					} catch (Exception e) {
						System.out.println("���ڸ� �Է����ּ���");
						continue;
					}

					if (choice_time == 1) {

						start_time = sc_time.get(no).split(" ~ ")[0];
						end_time = sc_time.get(no).split(" ~ ")[1];

						break;

					} else if (choice_time == 2) {
						continue;
					} else {
						System.out.println("������ ���� �߿��� �Է����ּ���");
					}

				} else {
					System.out.println("������ ���� �߿��� �Է����ּ���");
					continue;
				}

			}
			System.out.println("��������������������������������������������������������������������������������");
			// �¼� �Է¹ޱ�,cnt�� �ο������� ����(���߿�DTO�� ������ cnt�־��ָ� �ο����Էµ�)
			String alpha;
			char row = 'A';
			int col = 0, cnt = 0;
			int[][] seat = new int[5][5];
			String select_seat = "";
			ArrayList<String> check_seat = mdao.check_seat(theater_name, time4[choice - 1], movie_name, start_time);

			System.out.print("���� �ο� : ");
			int adult = sc.nextInt();
			System.out.print("��� �ο� : ");
			int child = sc.nextInt();
			int cnt_all = adult + child;
			System.out.println();

			while (true) {
				System.out.println("������������������������������������SCREEN����������������������������������");
				System.out.println();

				for (int i = 0; i < seat.length + 1; i++) {
					if (i == 0) {
						System.out.print("       ");
					} else if (i > 0) {
						System.out.print(" [ " + (i) + " ] ");
					}
				}
				for (int i = 0; i < seat.length; i++) {
					System.out.println();
					System.out.print(" [ " + (char) (i + 65) + " ]");
					if (mdao.update_seat(theater_name, time4[choice - 1], movie_name, start_time)) {
						for (int k = 0; k < check_seat.size(); k++) {
							String[] st = check_seat.get(k).split(" ");
							for (int j = 0; j < st.length; j++) {
								seat[st[j].charAt(0) - 'A'][st[j].charAt(1) - '1'] = 1;
							}

						}
					}
					for (int j = 0; j < seat.length; j++) {
						if (seat[i][j] == 0) {
							System.out.print("  [ �� ]");
						} else {
							System.out.print("  [ x ]");
						}
					}
				}
				System.out.println();
				System.out.println("��������������������������������������������������������������������������������");
				while (true) {
					System.out.print("�����Ͻ� �¼��� ���� �Է����ּ��� : ");
					alpha = sc.next();
					System.out.println("1.���� / 2.�ٽ��Է�");
					int choice_seat = sc.nextInt();
					if (choice_seat == 1) {
						break;
					} else if (choice_seat == 2) {
						continue;
					}
				}
				row = alpha.trim().charAt(0);
				System.out.println("�Է��� �� : " + row);

				if (row < 65 || row > 69) {
					System.out.println("��������������������������������������������������������������������������������");
					System.out.println("������ �� ���� ���Դϴ� / �ٽ� �Է����ּ���");
					continue;
				}
				while (true) {
					System.out.print("�����Ͻ� �¼��� ��ȣ�� �Է����ּ��� : ");
					col = sc.nextInt();
					System.out.println("1.���� / 2.�ٽ��Է�");
					int choice_seat = sc.nextInt();
					if (choice_seat == 1) {
						break;
					} else if (choice_seat == 2) {
						continue;
					}
				}
				int column = row - 65;
				if (col < 1 || col > 5) {
					System.out.println("��������������������������������������������������������������������������������");
					System.out.println("������ �� ���� �¼��Դϴ� / �ٽ� �Է����ּ���");
					continue;
				}

				boolean flag = false;
				if (mdao.update_seat(theater_name, time4[choice - 1], movie_name, start_time)) {
					for (int i = 0; i < check_seat.size(); i++) {
						String[] a = check_seat.get(i).split(" ");
						for (int j = 0; j < a.length; j++) {
							if (a[j].equals((row + "" + col))) {
								flag = true;
							}
						}
					}
				}
				if (flag) {
					System.out.println("�̹� ���Ű� �Ϸ�� �ڸ��Դϴ�");
					continue;
				}

				System.out.printf("�����Ͻ� �¼��� %c����  %d�� �¼��Դϴ� \n", row, col);

				System.out.println("1.���� / 2.���");
				int choice_seat = sc.nextInt();
				if (choice_seat == 1) {
					if (select_seat.equals((row + "" + col) + " ")) {
						System.out.println("�̹� ������ �Ͻ� ���Դϴ�");
						continue;
					}

					cnt++;
					if (cnt <= cnt_all) {
						select_seat += ((row + "" + col) + " ");
						seat[column][col - 1] = 1;
						if (cnt == cnt_all) {
							System.out.println(select_seat + "�¼��� �����ϼ̽��ϴ�");
							break;
						}
					}
				}
			}

			// ���� �ý���
			System.out.println("��������������������������������������������");

			ArrayList<Integer> sr_price = mdao.calc(sc_time.get(0).substring(3).trim());

			int price = (adult * sr_price.get(0) + child * sr_price.get(1));

			System.out.println("� :" + adult + "�� / ��� :" + child + "��\n�����ݾ� :" + price);

			if (Integer.parseInt(start_time.substring(0, 2)) < 10) {
				price = price - 4000;
				System.out.println("���� ��ȭ ����: 4000��");
			}else {
				Date now = new Date();
				Date c = new Date();
				now.setMonth(now.getMonth()+1);
				now.setDate(0);
				
				for (int i = now.getDate(); i >=1; i--) {
					c.setDate(i);
					if(c.getDay() == 3) {
						if(format1.format(new Date()).equals(format1.format(c))) {
							price = cnt_all*5000;
							System.out.println("��ȭ�� �� ���� ���� [��� ��ȭ�� 5õ���� ���Ű����մϴ�.]");
						}
						break;
					}
				}
			}

			System.out.println("[�� ���� �ݾ� ]:" + price);

			while (true) {

				System.out.print("\n���� �Ͻðڽ��ϱ�? 1.��  2.�ƴϿ�\n��ȣ�Է�:");

				try {
					sc = new Scanner(System.in);
					int last = sc.nextInt();

					if (last == 1) {

						if (theater_name != null && movie_name != null && sc_time.get(0) != null && start_time != null&& end_time != null && !select_seat.equals("") && price > 0 && cnt_all > 0) {

							ReservationDTO res = new ReservationDTO(loginUser.getUserid(), theater_name,time4[choice - 1], movie_name, sc_time.get(0), start_time, end_time, select_seat,cnt_all, price);

							if (price < loginUser.getUserpoint()) {

								int result2 = mdao.calcPoint(loginUser.getUserpoint() - price + price / 10,loginUser.getUserid());
								int result3 = mdao.plusViewer(cnt_all, choice_movie);

								if (result2 == 1 && result3 == 1) {

									int result = mdao.reservation(res);

									if (result == 1) {
										int count = udao.count_reserve();
										if(count >= 10 && count <30) {
											if(udao.level_change(count)) {
											System.out.println("[ȸ������� ��� ������� ��µǾ����ϴ�]");
											}
										} else if(count >= 30) {
											if(udao.level_change(count)) {
											System.out.println("[ȸ������� VIP ������� ��µǾ����ϴ�]");
											}
										}
										System.out.println("[���ſϷ� �Ǿ����ϴ�.����ȭ������ ���ư��ϴ�.]\n");
										
									} else {
										mdao.calcPoint(loginUser.getUserpoint() + price - price / 10,loginUser.getUserid());
										mdao.plusViewer(-cnt_all, choice_movie);
										System.out.println("���Ű� �����Ͽ����ϴ�.ó������ �ٽ� �õ����ּ���\n");
									}
								} else {

									if (result2 != 1 && result3 == 1) {
										mdao.plusViewer(-cnt_all, choice_movie);
									} else if (result3 != 1 && result2 == 1) {
										mdao.calcPoint(loginUser.getUserpoint() + price - price / 10,loginUser.getUserid());
									}
									System.out.println("������ �����Ͽ����ϴ�.ó������ �ٽ� �õ����ּ���\n");
								}

							} else {
								System.out.println(price - loginUser.getUserpoint() + "����Ʈ ����.����Ʈ ������ ���� ������������ �̵��մϴ�\n");

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
								}
								new MypageView();
							}

						} else {
							System.out.println("���Ű� �����Ͽ����ϴ�.ó������ �ٽ� �õ����ּ���\n");
						}
						break;
					} else if (last == 2) {
						System.out.println("���� ���.����ȭ������ ���ư��ϴ�\n");
						break;
					} else {
						System.out.println("������ ���� �߿��� �Է����ּ���");
						continue;
					}

				} catch (Exception e) {
					System.out.println("���ڸ� �Է����ּ���.");
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
