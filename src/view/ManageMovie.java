package view;

import java.util.Scanner;

import dao.M_MovieDAO;
import dto.M_MovieDTO;

public class ManageMovie {
	public ManageMovie() {
		Scanner sc = new Scanner(System.in);
		System.out.print("����������������������������MOVIE MENU MANAGE����������������������������\n"
				+ "1. ���\n2. ����\n3. ����\n4. ������\n=> ");
		M_MovieDAO mdao = new M_MovieDAO();
		int choice = sc.nextInt();
		int movie_no = 0;
		
		if(choice == 1) {	//��ȭ ���
		
			System.out.print("�߰� �� ��ȭ�� �̸��� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String movie_name = sc.nextLine();
			
			System.out.print("�߰� �� ��ȭ�� ������ �̸��� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String movie_director = sc.nextLine();
			
			System.out.print("�߰� �� ��ȭ�� ����� �̸��� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String movie_actor = sc.nextLine();
			
			System.out.print("�߰� �� ��ȭ�� �帣�� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String movie_genre = sc.nextLine();
			
			System.out.print("�߰� �� ��ȭ�� ���������� �Է��ϼ��� : ");
			int movie_age = sc.nextInt();
			
			System.out.print("�߰� �� ��ȭ�� ���۱����� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String movie_country = sc.nextLine();
			
			System.out.print("�߰� �� ��ȭ�� �������� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String movie_release = sc.nextLine();
			
			System.out.print("�߰� �� ��ȭ�� �󿵽ð��� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String movie_runningtime = sc.nextLine();
			
			System.out.print("�߰� �� ��ȭ�� ������û�ڸ� �Է��ϼ��� : ");
			int movie_viewers = sc.nextInt();
			
			System.out.print("�߰� �� ��ȭ�� ������ �Է��ϼ��� : ");
			double movie_rating = sc.nextDouble();
			
			M_MovieDTO newmovie = new M_MovieDTO(movie_no, movie_name, movie_director,
					movie_actor, movie_genre, movie_age, movie_country, movie_release,
					movie_runningtime, movie_viewers, movie_rating);
			
			mdao.insertMovie(newmovie);
			System.out.println("��� �Ϸ�!");
			
		} else if(choice == 2) {	//��ȭ ����
			System.out.println(mdao.movieList());
			
			System.out.print("���� �� ��ȭ�� ��ȣ�� �Է��ϼ��� : ");
			int select =sc.nextInt();
			
			System.out.print("���� �� ��ȭ�� �̸��� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String name = sc.nextLine();
			
			System.out.print("���� �� ��ȭ�� ������ �̸��� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String director = sc.nextLine();
			
			System.out.print("���� �� ��ȭ�� ����� �̸��� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String actor = sc.nextLine();
			
			System.out.print("���� �� ��ȭ�� �帣�� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String genre = sc.nextLine();
			
			System.out.print("���� �� ��ȭ�� ���������� �Է��ϼ��� : ");
			int age = sc.nextInt();
			
			System.out.print("���� �� ��ȭ�� ���۱����� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String country = sc.nextLine();
			
			System.out.print("���� �� ��ȭ�� �������� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String release = sc.nextLine();
			
			System.out.print("���� �� ��ȭ�� �󿵽ð��� �Է��ϼ��� : ");
			sc = new Scanner(System.in);
			String runningtime = sc.nextLine();
			
			System.out.print("���� �� ��ȭ�� ������û�ڸ� �Է��ϼ��� : ");
			int viewers = sc.nextInt();
			
			System.out.print("���� �� ��ȭ�� ������ �Է��ϼ��� : ");
			double rating = sc.nextDouble();
			
			mdao.updateMovie(select, name, director, actor, genre, age, country, release, runningtime, viewers, rating);
			System.out.println("���� �Ϸ�!");
			
		} else if(choice == 3) {	//��ȭ ����
			System.out.print(mdao.movieList());
			
			System.out.print("������ ��ȭ ��ȣ : ");
			int select1 = sc.nextInt();
			
			mdao.deleteMovie(select1);
			System.out.println("���� �Ϸ�!");
			
		} else if(choice == 4) {
			new ManageView();
		}
		
	}
}