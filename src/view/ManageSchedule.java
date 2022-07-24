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
			System.out.println("――――――――――――――――상영시간표 관리――――――――――――――――");
			System.out.println(
					"상영시간표 관리창 입니다.\n" + "아래의 메뉴를 선택 해 주세요." + "\n1. 시간표 등록\n2. 시간표 수정\n3. 시간표 삭제\n4. 나가기");
			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.print("――――――――――――――――시간표 등록――――――――――――――――");
				System.out.print(madao.theater());
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.print("새로 등록할 시간표의 지점의 번호를 적어 주세요 : ");
				in_theater = sc.nextInt();
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.print(madao.movie());
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.print("새로 등록할 시간표의 영화 번호를 적어주세요 : ");
				in_movie = sc.nextInt();
				sc = new Scanner(System.in);
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.println("새로 등록할 시간표의 관과 스크린타입을 적어 주세요 : ");
				in_screen = sc.nextLine();
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.println("새로 등록할 시간표의 시작시간을 적어 주세요 : ");
				in_time = sc.next();
				System.out.println("새로 등록할 시간표의 끝나는 시간을 적어 주세요 :");
				in_end_time = sc.next();
				
			} else if (choice == 2) {
				System.out.print("――――――――――――――――시간표 수정――――――――――――――――");
				System.out.print(madao.theater());
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.print("수정할 시간표가 속한 지점의 번호를 적어 주세요 : ");
				int up_theater = sc.nextInt();
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.print(madao.movie());
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.print("수정할 시간표의 영화의 번호를 적어주세요 : ");
				int up_movie = sc.nextInt();
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.print(madao.schedule(up_theater, up_movie));
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.print("수정할 시간표의 고유 번호를 적어 주세요  : ");
				up_no = sc.nextInt();
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.println("수정할 시간표의 시작시간을 적어 주세요 :");
				up_time = sc.next();
				System.out.println("수정할 시간표의 끝나는시간을 적어주세요 :");
				up_end_time = sc.next();
				
			} else if (choice == 3) {
				System.out.print("――――――――――――――――시간표 삭제――――――――――――――――");
				System.out.print(madao.theater());
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.println("삭제할 시간표가 속한 지점의 번호를 적어 주세요 : ");
				int del_theater = sc.nextInt();
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.print(madao.movie());
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.print("삭제할 시간표의 영화의 번호를 적어 주세요 : ");
				int del_movie = sc.nextInt();
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.print(madao.schedule(del_theater, del_movie));
				System.out.println("―――――――――――――――――――――――――――――――――――――――");
				System.out.print("삭제할 시간표의 고유번호를 적어 주세요 : ");
				del_no = sc.nextInt();
			} else {
				System.out.println("안녕히 가십쇼~");
				break;
			}
			ManagerDTO schedule = new ManagerDTO(in_theater, in_movie, in_screen, in_time, in_end_time, up_no, up_time, up_end_time, del_no);
			
			if(madao.ins_time(schedule)) {
				System.out.println("시간표 추가 되었습니다.");
			} else if (madao.up_time(schedule)) {
				System.out.println("시간표 수정 되었습니다.");
			} else if (madao.del_time(schedule)) {
				System.out.println("시간표 삭제 되었습니다.");
			} else {
				System.out.println("시간표 관리에 실패했습니다. 다시 확인해 주세요.");
			}
		}
	}

}
