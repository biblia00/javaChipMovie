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
			// 지점 정보 / 지점 선택
			int choice_theater = 0;
			String theater_name = "";
			while (true) {
				System.out.println("――――――――――――지점 정보――――――――――――");
				System.out.println(mdao.theater());

				System.out.print("지점 선택 : ");
				try {
					sc = new Scanner(System.in);
					choice_theater = sc.nextInt();
				} catch (Exception e) {
					System.out.println("보기에서 입력해주세요");
					continue;
				}
				theater_name = mdao.search_theater(choice_theater);
				if (choice_theater <= 10) {
					System.out.println("<" + theater_name + ">" + "지점을 선택하셨습니다");
					break;
				} else {
					System.out.println("보기에서 입력해주세요");
				}
			}

			// 영화 정보 / 영화 선택
			int choice_movie = 0;
			String movie_name = null;
			while (true) {
				System.out.println("───────────상영중인 영화───────────");
				System.out.println(mdao.movie());
				System.out.println("───────────────────────────────");
				System.out.print("영화 선택 : ");
				try {
					sc = new Scanner(System.in);
					choice_movie = sc.nextInt();
				} catch (Exception e) {
					System.out.println("보기에서 입력해주세요");
					continue;
				}
				if (choice_movie <= 6) {
					System.out.println(mdao.detail(choice_movie));
					System.out.println("────────────────────────────────────────");
					System.out.print("선택하신 영화로 예매하시겠습니까? 1.예매진행 2.돌아가기--->");
					int choice_reserve = 0;
					try {
						choice_reserve = sc.nextInt();
					} catch (Exception e) {
						System.out.println("보기에서 입력해주세요");
						continue;
					}
					if (choice_reserve == 1) {
						movie_name = mdao.search_movie(choice_movie);
						System.out.println("<" + movie_name + ">" + "을 선택하셨습니다");
						break;
					} else if (choice_reserve == 2) {
						continue;
					} else {
						System.out.println("보기에서 입력해주세요");
					}
				}
			}

			// 날짜 입력받기
			System.out.println("───────────────────────────────");
			String[] time4 = new String[5];
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
			
			while (true) {
				int time2 = 0;

				Date time = new Date();
				String time1 = format1.format(time);
				System.out.println("오늘은 <" + time1 + "> 입니다.");
				String time3 = time1.substring(0, 8);
				for (int i = 0; i < 5; i++) {
					time2 = Integer.parseInt(time1.substring(8, 10)) + i;
					time4[i] = (time3 + time2);
					System.out.println(i + 1 + "." + time3 + time2);
				}
				break;
			}
			System.out.print("예매하실 날을 선택해주세요 :");
			int choice = sc.nextInt();
			System.out.println(time4[choice - 1] + "을 선택하셨습니다.");

			// 고객에게 상영시간표 보여주기
			String start_time;
			String end_time;
			ArrayList<String> sc_time = mdao.schedule(choice_theater, choice_movie);

			while (true) {

				System.out.println("\n<<<<<<<영화 상영 시간표>>>>>>>");

				for (int i = 1; i < sc_time.size(); i++) {
					System.out.println(i + "." + sc_time.get(0) + "  " + sc_time.get(i));
				}

				System.out.println("상영시간을 선택해 주세요:");
				int no = 0;

				try {
					sc = new Scanner(System.in);
					no = sc.nextInt();
				} catch (Exception e) {
					System.out.println("숫자를 입력해주세요");
					continue;
				}

				if (no <= sc_time.size() && no >= 1) {

					System.out.print(sc_time.get(no) + " 선택하신 시간으로 예매하시겠습니까? 1.예매진행 2.돌아가기--->");

					int choice_time = 0;

					try {
						choice_time = sc.nextInt();
					} catch (Exception e) {
						System.out.println("숫자를 입력해주세요");
						continue;
					}

					if (choice_time == 1) {

						start_time = sc_time.get(no).split(" ~ ")[0];
						end_time = sc_time.get(no).split(" ~ ")[1];

						break;

					} else if (choice_time == 2) {
						continue;
					} else {
						System.out.println("보기의 숫자 중에서 입력해주세요");
					}

				} else {
					System.out.println("보기의 숫자 중에서 입력해주세요");
					continue;
				}

			}
			System.out.println("────────────────────────────────────────");
			// 좌석 입력받기,cnt로 인원수까지 세기(나중에DTO에 넣을때 cnt넣어주면 인원수입력됨)
			String alpha;
			char row = 'A';
			int col = 0, cnt = 0;
			int[][] seat = new int[5][5];
			String select_seat = "";
			ArrayList<String> check_seat = mdao.check_seat(theater_name, time4[choice - 1], movie_name, start_time);

			System.out.print("성인 인원 : ");
			int adult = sc.nextInt();
			System.out.print("어린이 인원 : ");
			int child = sc.nextInt();
			int cnt_all = adult + child;
			System.out.println();

			while (true) {
				System.out.println("──────────────────SCREEN─────────────────");
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
							System.out.print("  [ □ ]");
						} else {
							System.out.print("  [ x ]");
						}
					}
				}
				System.out.println();
				System.out.println("────────────────────────────────────────");
				while (true) {
					System.out.print("예약하실 좌석의 열을 입력해주세요 : ");
					alpha = sc.next();
					System.out.println("1.적용 / 2.다시입력");
					int choice_seat = sc.nextInt();
					if (choice_seat == 1) {
						break;
					} else if (choice_seat == 2) {
						continue;
					}
				}
				row = alpha.trim().charAt(0);
				System.out.println("입력한 열 : " + row);

				if (row < 65 || row > 69) {
					System.out.println("────────────────────────────────────────");
					System.out.println("선택할 수 없는 열입니다 / 다시 입력해주세요");
					continue;
				}
				while (true) {
					System.out.print("예약하실 좌석의 번호를 입력해주세요 : ");
					col = sc.nextInt();
					System.out.println("1.적용 / 2.다시입력");
					int choice_seat = sc.nextInt();
					if (choice_seat == 1) {
						break;
					} else if (choice_seat == 2) {
						continue;
					}
				}
				int column = row - 65;
				if (col < 1 || col > 5) {
					System.out.println("────────────────────────────────────────");
					System.out.println("선택할 수 없는 좌석입니다 / 다시 입력해주세요");
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
					System.out.println("이미 예매가 완료된 자리입니다");
					continue;
				}

				System.out.printf("선택하신 좌석은 %c열의  %d번 좌석입니다 \n", row, col);

				System.out.println("1.예약 / 2.취소");
				int choice_seat = sc.nextInt();
				if (choice_seat == 1) {
					if (select_seat.equals((row + "" + col) + " ")) {
						System.out.println("이미 선택을 하신 열입니다");
						continue;
					}

					cnt++;
					if (cnt <= cnt_all) {
						select_seat += ((row + "" + col) + " ");
						seat[column][col - 1] = 1;
						if (cnt == cnt_all) {
							System.out.println(select_seat + "좌석을 선택하셨습니다");
							break;
						}
					}
				}
			}

			// 결제 시스템
			System.out.println("──────────결제──────────");

			ArrayList<Integer> sr_price = mdao.calc(sc_time.get(0).substring(3).trim());

			int price = (adult * sr_price.get(0) + child * sr_price.get(1));

			System.out.println("어른 :" + adult + "명 / 어린이 :" + child + "명\n결제금액 :" + price);

			if (Integer.parseInt(start_time.substring(0, 2)) < 10) {
				price = price - 4000;
				System.out.println("조조 영화 할인: 4000원");
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
							System.out.println("문화의 날 할인 적용 [모든 영화를 5천원에 예매가능합니다.]");
						}
						break;
					}
				}
			}

			System.out.println("[총 결제 금액 ]:" + price);

			while (true) {

				System.out.print("\n결제 하시겠습니까? 1.예  2.아니오\n번호입력:");

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
											System.out.println("[회원등급이 골드 등급으로 상승되었습니다]");
											}
										} else if(count >= 30) {
											if(udao.level_change(count)) {
											System.out.println("[회원등급이 VIP 등급으로 상승되었습니다]");
											}
										}
										System.out.println("[예매완료 되었습니다.메인화면으로 돌아갑니다.]\n");
										
									} else {
										mdao.calcPoint(loginUser.getUserpoint() + price - price / 10,loginUser.getUserid());
										mdao.plusViewer(-cnt_all, choice_movie);
										System.out.println("예매가 실패하였습니다.처음부터 다시 시도해주세요\n");
									}
								} else {

									if (result2 != 1 && result3 == 1) {
										mdao.plusViewer(-cnt_all, choice_movie);
									} else if (result3 != 1 && result2 == 1) {
										mdao.calcPoint(loginUser.getUserpoint() + price - price / 10,loginUser.getUserid());
									}
									System.out.println("결제가 실패하였습니다.처음부터 다시 시도해주세요\n");
								}

							} else {
								System.out.println(price - loginUser.getUserpoint() + "포인트 부족.포인트 충전을 위해 마이페이지로 이동합니다\n");

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
								}
								new MypageView();
							}

						} else {
							System.out.println("예매가 실패하였습니다.처음부터 다시 시도해주세요\n");
						}
						break;
					} else if (last == 2) {
						System.out.println("결제 취소.메인화면으로 돌아갑니다\n");
						break;
					} else {
						System.out.println("보기의 숫자 중에서 입력해주세요");
						continue;
					}

				} catch (Exception e) {
					System.out.println("숫자만 입력해주세요.");
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
