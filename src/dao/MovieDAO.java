package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dto.ReservationDTO;
import dto.UserDTO;

public class MovieDAO {

	Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	public MovieDAO() {
		conn = DBConnection.getConnection();
	}

	// ��ü ���� �����ִ� �޼ҵ�
	public String theater() {
		String sql = "SELECT THEATER_NO,THEATER_NAME FROM THEATER";
		String result = "";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				result += rs.getInt("THEATER_NO") + "." + rs.getString("THEATER_NAME") + "\n";
			}
			System.out.println();
		} catch (SQLException e) {
		}
		return result;
	}

	// ������ ������ DB�� �ִ��� Ȯ�� �� result�� �� ������ �̸��� ��Ƽ� �������ִ� �޼ҵ�
	public String search_theater(int choice) {
		String sql = "SELECT THEATER_NAME FROM THEATER WHERE THEATER_NO=?";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, choice);
			rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getString("THEATER_NAME");
			}
		} catch (SQLException e) {
		}
		return result;
	}

	// ������ϰ� �ִ� ��ȭ��� ���� �����ִ� �޼ҵ�
	public String movie() {
		String sql = "SELECT MOVIE_NO,MOVIE_NAME FROM MOVIE WHERE MOVIE_RELEASE < '2022.01.01'";
		String result = "";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				result += rs.getInt("MOVIE_NO") + "." + rs.getString("MOVIE_NAME") + "\n";
			}
		} catch (SQLException e) {
		}
		return result;
	}

	// ����������ȭ ���� �����ִ� �޼ҵ�
	public String upcoming() {
		String sql = "SELECT MOVIE_NO,MOVIE_NAME FROM MOVIE " + "WHERE MOVIE_RELEASE > '2022.01.01'";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				result += rs.getInt("MOVIE_NO") + "." + rs.getString("MOVIE_NAME") + "\n";
			}
		} catch (SQLException e) {
		}
		return result;
	}

	// ��ü ��ȭ��� ���� �����ִ� �޼ҵ�
	public String movie_all() {
		String sql = "SELECT MOVIE_NO,MOVIE_NAME FROM MOVIE";
		String result = "";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				result += rs.getInt("MOVIE_NO") + "." + rs.getString("MOVIE_NAME") + "\n";
			}
		} catch (SQLException e) {
		}
		return result;
	}

	// ������ ��ȭ�� DB�� �ִ��� Ȯ�� �� result�� �� ��ȭ�� ������ ��Ƽ� �������ִ� �޼ҵ�
	public String search_movie(int choice) {
		String sql = "SELECT MOVIE_NAME FROM MOVIE WHERE MOVIE_NO=?";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, choice);
			rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getString("MOVIE_NAME");
			}
		} catch (SQLException e) {
		}
		return result;
	}

	// ��ȭ ��ȣ �Է¹޾Ƽ� ������ ������ �����ִ� �޼ҵ�
	public String detail(int choice) {
		String sql = "SELECT MOVIE_NAME,MOVIE_GENRE,MOVIE_AGE,MOVIE_RUNNINGTIME FROM MOVIE WHERE MOVIE_NO=?";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, choice);
			rs = ps.executeQuery();

			while (rs.next()) {
				result += "��ȭ���� : " + rs.getString("MOVIE_NAME") + "\n�帣 : " + rs.getString("MOVIE_GENRE")
						+ "\n��û���ɿ��� : " + rs.getInt("MOVIE_AGE") + "\n����Ÿ�� : " + rs.getString("MOVIE_RUNNINGTIME");
			}
		} catch (SQLException e) {
		}
		return result;
	}

	// ��ȭ ��ȣ �Է¹޾Ƽ� ������ ������ �����ִ� �޼ҵ�
	public String detail_more(int choice_movie) {
		String sql = "SELECT MOVIE_NAME,MOVIE_DIRECTOR,MOVIE_ACTOR,MOVIE_GENRE,"
				+ "MOVIE_AGE,MOVIE_COUNTRY,MOVIE_RELEASE,MOVIE_RUNNINGTIME,MOVIE_VIEWERS,MOVIE_RATING "
				+ "FROM MOVIE WHERE MOVIE_NO=?";
		String result = "";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, choice_movie);
			rs = ps.executeQuery();

			while (rs.next()) {
				result += "��ȭ���� : " + rs.getString("MOVIE_NAME") + "\n���� : " + rs.getString("MOVIE_DIRECTOR")
						+ "\n�⿬��� : " + rs.getString("MOVIE_ACTOR") + "\n�帣 : " + rs.getString("MOVIE_GENRE")
						+ "\n��û���ɿ��� : " + rs.getInt("MOVIE_AGE") + "\n���� : " + rs.getString("MOVIE_COUNTRY")
						+ "\n��ȭ���� : " + rs.getString("MOVIE_RELEASE") + "\n����Ÿ�� : " + rs.getString("MOVIE_RUNNINGTIME")
						+ "\n������û�� : " + rs.getString("MOVIE_VIEWERS") + "\n���� : " + rs.getString("MOVIE_RATING");
			}
		} catch (SQLException e) {
		}
		return result;
	}

	// �󿵽ð�ǥ ������ �����ִ� �޼ҵ�
	public ArrayList<String> schedule(int choice_theater, int choice_movie) {

		ArrayList<String> sc_time = new ArrayList<String>();

		String sql = "SELECT SCHEDULE_NO, SCHEDULE_SCREEN, SCHEDULE_START, SCHEDULE_END "
				+ "FROM MOVIE_SCHEDULE WHERE SCHEDULE_THEATER=? AND SCHEDULE_MOVIE=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, choice_theater);
			ps.setInt(2, choice_movie);
			rs = ps.executeQuery();

			while (rs.next()) {
				if (sc_time.size() == 0) {
					sc_time.add(0, rs.getString("SCHEDULE_SCREEN"));
				}
				sc_time.add(rs.getString("SCHEDULE_START") + " ~ " + rs.getString("SCHEDULE_END"));
			}

		} catch (SQLException e) {
		}
		return sc_time;
	}

	public ArrayList<Integer> calc(String screen) {

		ArrayList<Integer> sr_price = new ArrayList<Integer>();

		String sql = "SELECT SCREEN_ADULT, SCREEN_CHILD FROM SCREEN_PRICE WHERE SCREEN_NAME=?";

		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, screen);
			rs = ps.executeQuery();

			if (rs.next()) {
				sr_price.add(rs.getInt("SCREEN_ADULT"));
				sr_price.add(rs.getInt("SCREEN_CHILD"));
			}

		} catch (SQLException e) {
		}

		return sr_price;
	}

	public int reservation(ReservationDTO res) {

		int result = 0;
		String sql = "INSERT INTO RESERVATION VALUES(RESERVE_NO.NEXTVAL,?,?,?,?,?,?,?,?,?,?,TO_CHAR(SYSDATE, 'yyyy.mm.dd hh24:mi'),'���ſϷ�')";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, res.getReserve_user());
			ps.setString(2, res.getReserve_theater());
			ps.setString(3, res.getReserve_movie());
			ps.setString(4, res.getReserve_screen());
			ps.setString(5, res.getReserve_date());
			ps.setString(6, res.getSchedule_start());
			ps.setString(7, res.getSchedule_end());
			ps.setString(8, res.getReserve_seat());
			ps.setInt(9, res.getReserve_people());
			ps.setInt(10, res.getReserve_price());

			result = ps.executeUpdate();
		} catch (SQLException e) {
		}

		return result;
	}

	public int calcPoint(int price, String loginUser) {
		int result = 0;

		String sql = "UPDATE USERDATA SET USER_POINT = ? WHERE USER_ID = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, price);
			ps.setString(2, loginUser);

			result = ps.executeUpdate();
		} catch (SQLException e) {
		}

		return result;
	}

	public String event() {
		String sql = "SELECT * FROM EVENT";
		String result = "";

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				result += "��" + rs.getString(1) + "��\n" + rs.getString(2) + "\n\n";
			}
		} catch (SQLException e) {
		}

		return result;
	}

	public ArrayList<String> check_seat(String theater_name, String reserve_date, String movie_name,
			String start_time) {
		ArrayList<String> check_seat = new ArrayList<String>();
		String sql = "SELECT RESERVE_SEAT FROM RESERVATION "
				+ "WHERE RESERVE_THEATER=? AND RESERVE_DATE=? AND RESERVE_MOVIE=? AND SCHEDULE_START=? AND RESERVE_STATUS='���ſϷ�'";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, theater_name);
			ps.setString(2, reserve_date);
			ps.setString(3, movie_name);
			ps.setString(4, start_time);
			rs = ps.executeQuery();

			while (rs.next()) {
				check_seat.add(rs.getString("RESERVE_SEAT"));
			}
		} catch (SQLException e) {
		}
		return check_seat;
	}

	public boolean update_seat(String theater_name, String reserve_date, String movie_name, String start_time) {
		String sql = "SELECT RESERVE_SEAT FROM RESERVATION WHERE RESERVE_THEATER=? AND RESERVE_DATE=? AND RESERVE_MOVIE=? "
				+ "AND SCHEDULE_START=? AND RESERVE_STATUS='���ſϷ�'";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, theater_name);
			ps.setString(2, reserve_date);
			ps.setString(3, movie_name);
			ps.setString(4, start_time);

			rs = ps.executeQuery();

			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
		}
		return false;
	}

	public int plusViewer(int i, int choice_movie) {

		int result = 0;

		String sql = "UPDATE MOVIE SET MOVIE_VIEWERS = MOVIE_VIEWERS+? WHERE MOVIE_NO = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, i);
			ps.setInt(2, choice_movie);

			result = ps.executeUpdate();
		} catch (SQLException e) {
		}

		return result;
	}

	public ArrayList<String> check_movie(int check_movie_no) {
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		String sql = "SELECT RESERVE_MOVIE, RESERVE_PEOPLE FROM RESERVATION WHERE RESERVE_USER=? AND RESERVE_NO=? AND RESERVE_STATUS='�������'";
		ArrayList<String> reserve_info = new ArrayList<String>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginUser.getUserid());
			ps.setInt(2, check_movie_no);

			rs = ps.executeQuery();

			while (rs.next()) {
				reserve_info.add(rs.getString("RESERVE_MOVIE"));
				reserve_info.add(rs.getString("RESERVE_PEOPLE"));
			}
		} catch (SQLException e) {
		}
		return reserve_info;
	}

	public void minusViewer(String check_movie, int check_people) {
		String sql = "UPDATE MOVIE SET MOVIE_VIEWERS = MOVIE_VIEWERS-? WHERE MOVIE_NAME=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, check_people);
			ps.setString(2, check_movie);

			ps.executeUpdate();

		} catch (SQLException e) {
		}

	}

}
