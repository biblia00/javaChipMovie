package view;

import java.util.Scanner;

//�ڹ�Ĩ ���� ù ����ȭ��
public class Index {
    public static void main(String[] args) {
        System.out.println("ȯ���մϴ�~ �ڹ�Ĩ ��ȭ���Դϴ�~^0^");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Join\n2. Login\n3. Find-ID\n4. Find-PASSWORD\n5. Exit\n0. Manager");
            int choice = sc.nextInt();

            if (choice == 1) {
                // ȸ������
                new JoinView();
            } else if (choice == 2) {
                // �α���
                new LoginView();
            } else if (choice == 3) {
                // ���̵� ã��
                new FindIDView();
            } else if (choice == 4) {
                // ��й�ȣ ã��
                new FindPWView();
            } else if (choice == 5) {
                // ������
                System.out.println("ã���ּż� �����մϴ� �ȳ��� ������ ^0^");
                break;
            } else if (choice == 0) {
            	new ManagerPage();
            }
        }
    }

}
