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
			System.out.println("―――――――――――――――――――――지점 관리―――――――――――――――――――――");
			System.out.println("지점 관리 페이지입니다.\n" +"1. 지점 등록하기\n" + "2. 지점 수정하기\n" + "3. 지점 삭제하기\n" + "4. 나가기");
			int choice = sc.nextInt();
			
			if (choice == 1) {
				System.out.println("――――――――――――――지점 등록――――――――――――――");
				System.out.println(mdao.theater());
				System.out.print("새로운 지점을 적어 주세요 : ");
				add_theater = sc.next();
				if(mdao.check_theater(add_theater)) {
					ManagerDTO site = new ManagerDTO(add_theater);
				if(mdao.add_theater(site)) {
					System.out.println("지점이 추가 되었습니다.");
				}else {
					System.out.println("지점 추가를 실패 하셨습니다.");
				}
					
			}else {
				System.out.println("중복되는 지점이 있습니다.");
			}
				
			}else if (choice == 2){
				System.out.println("――――――――――――――지점 수정――――――――――――――");
				System.out.println(mdao.theater());
				System.out.print("수정 할 지점을 선택 바랍니다 : ");
				up_theater = sc.nextInt();
				System.out.print("등록할 지점을 적어주세요  : ");
				in_theater = sc.next();
				if(mdao.change_theater(up_theater,in_theater)) {
					System.out.println("지점 수정이 완료 되었습니다.");
				}else {
					System.out.println("지점 수정을 실패 하셨습니다.");
				}

				
			}else if (choice == 3) {
				System.out.println("――――――――――――――지점 삭제――――――――――――――");
				System.out.println(mdao.theater());
				System.out.print("삭제 할 지점을 선택 바랍니다 : ");
				del_theater = sc.nextInt();
				if(mdao.del_theater(del_theater)) {
					System.out.println("지점이 삭제 되었습니다.");
				}else {
					System.out.println("지점 삭제을 실패 하셨습니다.");
				}
				
			}else {
				System.out.println("안녕히 가세요 ~~");
				new ManageView();
			}
			
			
			
			
				
		}
	}

}

