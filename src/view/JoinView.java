package view;

import java.util.Scanner;

import dto.UserDTO;

import java.util.Scanner;
import java.util.regex.Pattern;

import dao.UserDAO;
import dto.UserDTO;

public class JoinView {
	public JoinView() {
		System.out.println("������������������������������JOIN������������������������������");
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		String userpw = "";
		int userpoint = 0;
		String userlevel = "�Ϲ�";

		System.out.print("ID�� �Է����ּ��� : ");
		String userid = sc.next();
		if (udao.checkId(userid)) {
			while (true) {
				System.out.print("PASSWORD�� �Է����ּ��� : ");
				userpw = sc.next();
				System.out.print("PASSWORD�� Ȯ�����ּ��� : ");
				String userpw_re = sc.next();

				if (userpw.equals(userpw_re)) {
					break;
				} else {
					System.out.println("��й�ȣ�� �ٽ� Ȯ�����ּ���.");
				}
			}
			System.out.println("��������������������������������������������������������������������");
			System.out.print("�̸��� �Է����ּ��� : ");
			String username = sc.next();

			System.out.print("�г����� �Է����ּ��� : ");
			String usernkname = sc.next();
			if (udao.checkNKname(usernkname)) {

				System.out.println("���� ������ YYYY.MM.DD �������� �Է� �� �ּ��� : ");
				String birth = sc.next();
				while (!(udao.isBirth(birth))) {
					System.out.println("���Ŀ� ���� �ʽ��ϴ�. �ٽ� �Է� �� �ּ��� (ex : YYYY.MM.DD) :");
					birth = sc.next();
				}

				System.out.println("������ ���� �� �ּ��� :" + "\n�� ����\n�� ���� ");
				String gender = sc.next();
				System.out.println("��������������������������������������������������������������������");

				System.out.println("�̿��ϴ� ��Ż縦 ���� �� �ּ��� : " + "\n�� SKT\n�� KT\n�� LG U+");
				String phagency = sc.next();

				System.out.println("�ڵ��� ��ȣ�� �Է����ּ��� (ex:010-0000-0000): ");
				String userphone = sc.next();
				// ��ȭ��ȣ ���� �˻� (010-0000-0000)
				while (!(udao.isPhone(userphone))) {
					System.out.println("���Ŀ� ���� �ʽ��ϴ�. �ٽ� �Է����ּ���(ex: 010-0000-0000) :");
					userphone = sc.next();
				}

				System.out.println("��������������������������������������������������������������������");
				System.out.print("e-mail�� �Է����ּ��� : ");
				String useremail = sc.next();
				// �̸��� ���� �˻�
				while (!(udao.isEmail(useremail))) {
					System.out.println("���Ŀ� ���� �ʽ��ϴ�. �ٽ� �Է����ּ��� (ex : javachip@cinema.com) : ");
					useremail = sc.next();
				}

				System.out.print("�ּҸ� �Է����ּ��� : ");
				// �� ���� �޾Ҵ� �� ����ֱ�
				sc = new Scanner(System.in);
				String useraddr = sc.nextLine();
				System.out.println("��������������������������������������������������������������������");

				System.out.println("���̵�, ��й�ȣ�� ã�� ���� ������ �Դϴ�.\n " + "�Ʒ� ������ ��� �ּ��� (���ڷ� �Է� �� �ּ���)\n"
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

				UserDTO newUser = new UserDTO(userid, userpw, username, usernkname, birth, gender, phagency, userphone,
						useremail, useraddr, userpoint, userlevel, answer);

				if (udao.join(newUser)) {
					System.out.println(username + "�� �ݰ����ϴ�~\n" + "ȸ�������� ���������� �Ϸ�Ǿ����ϴ�.");
				} else {
					System.out.println("ȸ������ ���� / �ٽ� Ȯ�� �� �ּ���.");
				}
			} else {
				System.out.println("�г����� �ߺ��Ǿ����ϴ�. �ٽ� Ȯ�� ���ּ��� !");
			}
		} else {
			System.out.println("���̵� �ߺ� �Ǿ����ϴ�. �ٽ� Ȯ�� ���ּ��� !");
		}
	}
}
