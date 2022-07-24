package view;

import java.util.Scanner;

import dao.M_MovieDAO;
import dto.M_MovieDTO;

public class ManageMovie {
	public ManageMovie() {
		Scanner sc = new Scanner(System.in);
		System.out.print("──────────────MOVIE MENU MANAGE──────────────\n"
				+ "1. 등록\n2. 수정\n3. 삭제\n4. 나가기\n=> ");
		M_MovieDAO mdao = new M_MovieDAO();
		int choice = sc.nextInt();
		int movie_no = 0;
		
		if(choice == 1) {	//영화 등록
		
			System.out.print("추가 할 영화의 이름을 입력하세요 : ");
			sc = new Scanner(System.in);
			String movie_name = sc.nextLine();
			
			System.out.print("추가 할 영화의 감독의 이름을 입력하세요 : ");
			sc = new Scanner(System.in);
			String movie_director = sc.nextLine();
			
			System.out.print("추가 할 영화의 배우의 이름을 입력하세요 : ");
			sc = new Scanner(System.in);
			String movie_actor = sc.nextLine();
			
			System.out.print("추가 할 영화의 장르를 입력하세요 : ");
			sc = new Scanner(System.in);
			String movie_genre = sc.nextLine();
			
			System.out.print("추가 할 영화의 연령제한을 입력하세요 : ");
			int movie_age = sc.nextInt();
			
			System.out.print("추가 할 영화의 제작국가를 입력하세요 : ");
			sc = new Scanner(System.in);
			String movie_country = sc.nextLine();
			
			System.out.print("추가 할 영화의 개봉일을 입력하세요 : ");
			sc = new Scanner(System.in);
			String movie_release = sc.nextLine();
			
			System.out.print("추가 할 영화의 상영시간을 입력하세요 : ");
			sc = new Scanner(System.in);
			String movie_runningtime = sc.nextLine();
			
			System.out.print("추가 할 영화의 누적시청자를 입력하세요 : ");
			int movie_viewers = sc.nextInt();
			
			System.out.print("추가 할 영화의 평점을 입력하세요 : ");
			double movie_rating = sc.nextDouble();
			
			M_MovieDTO newmovie = new M_MovieDTO(movie_no, movie_name, movie_director,
					movie_actor, movie_genre, movie_age, movie_country, movie_release,
					movie_runningtime, movie_viewers, movie_rating);
			
			mdao.insertMovie(newmovie);
			System.out.println("등록 완료!");
			
		} else if(choice == 2) {	//영화 수정
			System.out.println(mdao.movieList());
			
			System.out.print("수정 할 영화의 번호를 입력하세요 : ");
			int select =sc.nextInt();
			
			System.out.print("수정 할 영화의 이름을 입력하세요 : ");
			sc = new Scanner(System.in);
			String name = sc.nextLine();
			
			System.out.print("수정 할 영화의 감독의 이름을 입력하세요 : ");
			sc = new Scanner(System.in);
			String director = sc.nextLine();
			
			System.out.print("수정 할 영화의 배우의 이름을 입력하세요 : ");
			sc = new Scanner(System.in);
			String actor = sc.nextLine();
			
			System.out.print("수정 할 영화의 장르를 입력하세요 : ");
			sc = new Scanner(System.in);
			String genre = sc.nextLine();
			
			System.out.print("수정 할 영화의 연령제한을 입력하세요 : ");
			int age = sc.nextInt();
			
			System.out.print("수정 할 영화의 제작국가를 입력하세요 : ");
			sc = new Scanner(System.in);
			String country = sc.nextLine();
			
			System.out.print("수정 할 영화의 개봉일을 입력하세요 : ");
			sc = new Scanner(System.in);
			String release = sc.nextLine();
			
			System.out.print("수정 할 영화의 상영시간을 입력하세요 : ");
			sc = new Scanner(System.in);
			String runningtime = sc.nextLine();
			
			System.out.print("수정 할 영화의 누적시청자를 입력하세요 : ");
			int viewers = sc.nextInt();
			
			System.out.print("수정 할 영화의 평점을 입력하세요 : ");
			double rating = sc.nextDouble();
			
			mdao.updateMovie(select, name, director, actor, genre, age, country, release, runningtime, viewers, rating);
			System.out.println("수정 완료!");
			
		} else if(choice == 3) {	//영화 삭제
			System.out.print(mdao.movieList());
			
			System.out.print("삭제할 영화 번호 : ");
			int select1 = sc.nextInt();
			
			mdao.deleteMovie(select1);
			System.out.println("삭제 완료!");
			
		} else if(choice == 4) {
			new ManageView();
		}
		
	}
}