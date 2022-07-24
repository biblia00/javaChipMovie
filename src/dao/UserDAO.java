package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import dto.UserDTO;

// �޼ҵ常 ����
public class UserDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	public UserDAO() {
		// �����Ǿ��ٸ� ������ �ٸ� �޾Ƽ� conn�� �־��ֱ�
		conn = DBConnection.getConnection();
	}

	public boolean join(UserDTO newUser) {
		int result = 0;
		String sql = "INSERT INTO USERDATA VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, newUser.getUserid());
			ps.setString(2, encrypt(newUser.getUserpw()));
			ps.setString(3, newUser.getUsername());
			ps.setString(4, newUser.getUsernkname());
			ps.setString(5, newUser.getBirth());
			ps.setString(6, newUser.getGender());
			ps.setString(7, newUser.getPhagency());
			ps.setString(8, newUser.getUserphone());
			ps.setString(9, newUser.getUsermail());
			ps.setString(10, newUser.getUseraddr());
			ps.setInt(11, newUser.getUserpoint());
			ps.setString(12, newUser.getUserlevel());
			ps.setString(13, newUser.getAnswer());

			// ������ �� �� ����
			result = ps.executeUpdate();
		} catch (SQLException e) {
		}

		return result == 1;
	}

	// ���̵� �ߺ� �˻�
	public boolean checkId(String userid) {
		String sql = "SELECT * FROM USERDATA WHERE  USER_ID=?";
		boolean result = false;
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, userid);
			// SELECT���̱� ������ executeQuery()
			rs = ps.executeQuery();
			result = !(rs.next());

		} catch (SQLException e) {
		}
		return result;
	}

	// �г��� �ߺ� �˻�
	public boolean checkNKname(String usernkname) {
		String sql = "SELECT * FROM USERDATA WHERE USER_NICKNAME=?";
		boolean result = false;
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, usernkname);
			// SELECT���̱� ������ executeQuery()
			rs = ps.executeQuery();
			result = !(rs.next());

		} catch (SQLException e) {
		}
		return result;
	}

	// �α���
	public boolean login(String userid, String userpw) {
		String sql = "SELECT * FROM USERDATA WHERE USER_ID =? AND USER_PW =?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, userid);
			ps.setString(2, encrypt(userpw));

			rs = ps.executeQuery();

			if (rs.next()) {
				UserDTO loginUser = new UserDTO(rs.getString("USER_ID"), decrypt(rs.getString("USER_PW")),
						rs.getString("USER_NAME"), rs.getString("USER_NICKNAME"), rs.getString("USER_BIRTH"),
						rs.getString("USER_GENDER"), rs.getString("USER_AGENCY"), rs.getString("USER_PHONE"),
						rs.getString("USER_EMAIL"), rs.getString("USER_ADDR"), rs.getInt("USER_POINT"),
						rs.getString("USER_LEVEL"), rs.getString("USER_ANSWER"));

				Session.put("loginUser", loginUser);
				return true;
			}
		} catch (SQLException e) {
		}
		return false;
	}

	// ��ȣȭ
	public String encrypt(String userpw) {
		String result = "";
		for (int i = 0; i < userpw.length(); i++) {
			result += (char) (userpw.charAt(i) + 4);
		}
		return result;
	}

	// ��ȣȭ
	public String decrypt(String en_pw) {
		String result = "";
		for (int i = 0; i < en_pw.length(); i++) {
			result += (char) (en_pw.charAt(i) - 4);
		}
		return result;
	}

	// ��ȭ��ȣ ���� �˻� �޼ҵ� (000-0000-0000)
	public boolean isPhone(String phone) {
		return Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phone);
	}

	// �̸��� ���� �˻� �޼ҵ� (~~~@~~~~)
	public boolean isEmail(String email) {
		return Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
				email);
	}

	// ������� YYYY.MM.DD ���� �˻� �޼ҵ�
	public boolean isBirth(String birth) {
		return Pattern.matches("^((19|20)\\d\\d)?([.])?(0[1-9]|1[012])([.])?(0[1-9]|[12][0-9]|3[01])$", birth);
	}

	public boolean leaveId(String user_id, String user_pw) {
		String sql = "DELETE FROM USERDATA WHERE USER_ID=? AND USER_PW =?";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, encrypt(user_pw));
			result = ps.executeUpdate();
		} catch (SQLException e) {
		}
		return result == 1;
	}

	public boolean modifyUser(String user_id, int col, String newData) {
		String[] cols = { "", "USER_PW", "", "", "", "", "", "USER_PHONE", "USER_EMAIL", "USER_ADDR" };
		String sql = "UPDATE USERDATA SET " + cols[col] + " = ? WHERE USER_ID=?";

		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newData);
			ps.setString(2, user_id);

			result = ps.executeUpdate();
		} catch (SQLException e) {
		}
		if (col == 7) {
			((UserDTO) Session.get("loginUser")).setUserphone(newData);
		} else if (col == 8) {
			((UserDTO) Session.get("loginUser")).setUsermail(newData);
		} else if (col == 9) {
			((UserDTO) Session.get("loginUser")).setUseraddr(newData);
		}
		return result == 1;
	}

	public boolean charge(int charge_point) {
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		String sql = "UPDATE USERDATA SET USER_POINT=? WHERE USER_ID=?";
		int result = 0;
		int point = charge_point + loginUser.getUserpoint();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, point);
			ps.setString(2, loginUser.getUserid());
			result = ps.executeUpdate();

		} catch (SQLException e) {
		}
		((UserDTO) Session.get("loginUser")).setUserpoint(point);
		return result == 1;
	}

	public boolean refund(int refund_point) {
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		String sql = "UPDATE USERDATA SET USER_POINT=? WHERE USER_ID=?";
		boolean result = false;
		int point = 0;
		if (loginUser.getUserpoint() >= refund_point) {
			point = loginUser.getUserpoint() - refund_point;
			result = true;
		} else {
			return result;
		}
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, point);
			ps.setString(2, loginUser.getUserid());
			ps.executeUpdate();
		} catch (SQLException e) {
		}
		((UserDTO) Session.get("loginUser")).setUserpoint(point);
		return result;
	}

	public void save(int pay) {
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		String sql = "UPDATE USERDATA SET USER_POINT=? WHERE USER_ID=?";
		int point = (int) ((pay * 0.1) + loginUser.getUserpoint());
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, point);
			ps.setString(2, loginUser.getUserid());
			ps.executeUpdate();
		} catch (SQLException e) {
		}
		((UserDTO) Session.get("loginUser")).setUserpoint(point);
	}

	public boolean pay(int pay) {
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		String sql = "UPDATE USERDATA SET USER_POINT=? WHERE USER_ID=?";
		boolean result = false;
		int point = 0;
		if (loginUser.getUserpoint() >= pay) {
			point = loginUser.getUserpoint() - pay;
			result = true;
		} else if (loginUser.getUserpoint() <= pay) {
			return result;
		}
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, point);
			ps.setString(2, loginUser.getUserid());
			ps.executeUpdate();
		} catch (SQLException e) {
		}
		((UserDTO) Session.get("loginUser")).setUserpoint(point);
		return result;
	}

	public String findID(String username, String birth, String phagency, String userphone, String answer) {
		String sql = "SELECT USER_ID FROM USERDATA WHERE USER_NAME = ? AND USER_BIRTH = ? AND USER_AGENCY = ? AND USER_PHONE = ? AND USER_ANSWER = ?";
		String result = "";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ps.setString(2, birth);
			ps.setString(3, phagency);
			ps.setString(4, userphone);
			ps.setString(5, answer);

			rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getString(1);
			} else {
				result = null;
			}
		} catch (SQLException e) {
		}
		return result;
	}

	public String findPW(String userid, String username, String birth, String phagency, String userphone,
			String answer) {
		String sql = "SELECT USER_PW FROM USERDATA "
				+ "WHERE USER_ID = ? AND USER_NAME = ? AND USER_BIRTH = ? AND USER_AGENCY = ? AND USER_PHONE = ? AND USER_ANSWER = ?";
		String result = "";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, userid);
			ps.setString(2, username);
			ps.setString(3, birth);
			ps.setString(4, phagency);
			ps.setString(5, userphone);
			ps.setString(6, answer);

			rs = ps.executeQuery();

			if (rs.next()) {
				result = decrypt(rs.getString(1));
			} else {
				result = null;
			}
		} catch (SQLException e) {
		}
		return result;
	}

	public String reserve_movie() {
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		String sql = "SELECT * FROM RESERVATION WHERE RESERVE_USER=? AND RESERVE_STATUS='���ſϷ�'";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginUser.getUserid());
			rs = ps.executeQuery();

			while (rs.next()) {
				result += "���Ź�ȣ :" + rs.getInt("RESERVE_NO") + "(" + rs.getString("RESERVE_USER") + ")��\n"
						+ rs.getString("RESERVE_THEATER") + "  " + rs.getString("RESERVE_MOVIE") + "  "
						+ rs.getString("RESERVE_SCREEN") + "\n���� :" + rs.getString("RESERVE_DATE") + " / �ð� : "
						+ rs.getString("SCHEDULE_START") + "~" + rs.getString("SCHEDULE_END") + "\n�¼� : "
						+ rs.getString("RESERVE_SEAT") + "(" + rs.getString("RESERVE_PEOPLE") + "��)\n"
						+ rs.getString("RESERVE_STATUS") + "\n��������������������������������������������������������������������������\n";
			}
			;
		} catch (SQLException e) {
		}
		return result;
	}

	public boolean movie_cancel(int check_movie_no) {
		UserDTO loginUser = (UserDTO)Session.get("loginUser");
		String sql = "UPDATE RESERVATION SET RESERVE_STATUS='�������' WHERE RESERVE_USER=? AND RESERVE_NO=?";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginUser.getUserid());
			ps.setInt(2, check_movie_no);
			result = ps.executeUpdate();
		} catch (SQLException e) {
		}
		return result == 1;
	}

	public boolean refund_movie(int check_movie_no) {
		UserDTO loginUser = (UserDTO)Session.get("loginUser");
		String sql = "UPDATE USERDATA SET USER_POINT=? WHERE USER_ID=?";
		int result = 0;
		int update_point = loginUser.getUserpoint()+check_price(check_movie_no);
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, update_point);
			ps.setString(2, loginUser.getUserid());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
		}
		((UserDTO)Session.get("loginUser")).setUserpoint(update_point);
		return result == 1;
	}

	public int check_price(int check_movie_no) {
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		String sql = "SELECT RESERVE_PRICE FROM RESERVATION WHERE RESERVE_USER=? AND RESERVE_NO=?";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginUser.getUserid());
			ps.setInt(2, check_movie_no);

			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("RESERVE_PRICE");
			}
		} catch (SQLException e) {
		}
		return result;
	}

	public String reserve_food() {
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		String sql = "SELECT * FROM FOOD_ORDER WHERE ORDER_USER=? AND ORDER_STATUS='�ֹ� �Ϸ�'";
		String result ="";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginUser.getUserid());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result += "�ֹ���ȣ : " + rs.getInt("ORDER_NUM") +"\n���� : "
				+ rs.getString("ORDER_USER") + "\n���� : "
				+ rs.getString("FOODS") + "\n���� : "
				+ rs.getInt("PRICE_SUM") + "��\n"
				+ rs.getString("ORDER_TIME") + "\n"
				+ rs.getString("ORDER_STATUS") + "\n��������������������������������������������������������������������������\n";
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public boolean food_cancel(int check_food_no) {
		UserDTO loginUser = (UserDTO)Session.get("loginUser");
		String sql = "UPDATE FOOD_ORDER SET ORDER_STATUS='�ֹ� ���' WHERE ORDER_USER=? AND ORDER_NUM=?";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginUser.getUserid());
			ps.setInt(2, check_food_no);
			result = ps.executeUpdate();
		} catch (SQLException e) {
		}
		return result == 1;
	}
	
	public boolean refund_food(int check_food_no) {
		UserDTO loginUser = (UserDTO)Session.get("loginUser");
		String sql = "UPDATE USERDATA SET USER_POINT=? WHERE USER_ID=?";
		int result = 0;
		int update_point = loginUser.getUserpoint()+check_price_food(check_food_no);
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, update_point);
			ps.setString(2, loginUser.getUserid());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
		}
		((UserDTO)Session.get("loginUser")).setUserpoint(update_point);
		return result == 1;
	}
	
	public int check_price_food(int check_food_no) {
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		String sql = "SELECT PRICE_SUM FROM FOOD_ORDER WHERE ORDER_USER=? AND ORDER_NUM=?";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginUser.getUserid());
			ps.setInt(2, check_food_no);

			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("PRICE_SUM");
			}
		} catch (SQLException e) {
		}
		return result;
	}

	public int count_reserve() {
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		int result = 0;
		String sql = "SELECT SUM(RESERVE_PEOPLE) FROM RESERVATION WHERE RESERVE_USER=? AND RESERVE_STATUS='���ſϷ�'";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, loginUser.getUserid());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getInt("SUM(RESERVE_PEOPLE)");
			}
		} catch (SQLException e) {
		}
		return result;
	
	}

	public boolean level_change(int count) {
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		String sql = "UPDATE USERDATA SET USER_LEVEL=? WHERE USER_ID=?";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			if(count >= 10 && count <30) {
				ps.setString(1, "���");
			} else if(count >= 30) {
				ps.setString(1, "VIP");
			} else {
				ps.setString(1, "�Ϲ�");
			}
			ps.setString(2, loginUser.getUserid());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
		}
		return result == 1;
	}

}
