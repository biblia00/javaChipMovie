package view;

import java.util.Scanner;

import dao.UserDAO;
import dto.UserDTO;

public class FindIDView {
	public FindIDView() {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		String find_ID = "";

		System.out.print("�̸��� �Է��� �ּ��� : ");
		String username = sc.next();

		System.out.println("��������� YYYY.MM.DD �������� �Է��� �ּ��� : ");
		String birth = sc.next();
		while (!(udao.isBirth(birth))) {
			System.out.println("���Ŀ� ���� �ʽ��ϴ�. �ٽ� �Է� �� �ּ��� (ex : YYYY.MM.DD) :");
			birth = sc.next();
		}

		System.out.println("�̿��ϴ� ��Ż縦 ���� �� �ּ��� : " + "\n�� SKT\n�� KT\n�� LG U+");
		String phagency = sc.next();

		System.out.println("�޴��� ��ȣ�� �Է��� �ּ��� (ex:010-0000-0000) : ");
		String userphone = sc.next();
		while (!(udao.isPhone(userphone))) {
			System.out.println("���Ŀ� ���� �ʽ��ϴ�. �ٽ� �Է����ּ���(ex: 010-0000-0000) :");
			userphone = sc.next();
		}

		System.out.println("ȸ�����Խ� ������ ������ ����ּ���(���ڷ� �Է� �� �ּ���)\n"
				+ "1. ���� ��￡ ���� ������ ������?\n" + "2. �ڽ��� �λ� �¿����?\n" + "3. ���� �����ϴ� �ι��� �̸���?\n"
				+ "4. ���� �����ϴ� ĳ���ʹ�?\n" + "5. ���� or � �� Ű�� �ݷ����� �̸���?");
		int choice = sc.nextInt();
		String answer = "";
		if (choice == 1) {
			System.out.println("������ ���� ���� ���� �ּ���.\n " + "���� ��￡ ���� ������ ������?\n" + "");
			answer = sc.next();
		} else if (choice == 2) {
			System.out.println("������ ���� ���� �����ּ���.\n " + "�ڽ��� �λ� �¿����?");
			answer = sc.next();
		} else if (choice == 3) {
			System.out.println("������ ���� ���� �����ּ���.\n" + "���� �����ϴ� �ι��� �̸���?");
			answer = sc.next();
		} else if (choice == 4) {
			System.out.println("������ ���� ���� �����ּ���.\n" + "���� �����ϴ� ĳ���ʹ� ?");
			answer = sc.next();
		} else if (choice == 5) {
			System.out.println("������ ���� ���� �����ּ���.\n" + "���� or � �� Ű�� �ݷ����� �̸���?");
			answer = sc.next();
		}
		
		find_ID = udao.findID(username, birth, phagency, userphone, answer);
		
		if(find_ID != null) {
			System.out.println("["+username+"]" + "���� ã���ô� ���̵�� " + "["+find_ID+"]" + "�Դϴ�!");
		}  else {
			System.out.println("�Է��Ͻ� ������ ���� �ʽ��ϴ�. �ٽ� Ȯ���ؼ� ���� �� �ּ��� !");
		}
		
	
	}
}
