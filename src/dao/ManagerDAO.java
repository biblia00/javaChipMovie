package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dto.ManagerDTO;

public class ManagerDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	public ManagerDAO() {
		conn = DBConnection.getConnection();
	}

	// 상영시간표 추가하는 메소드
	public boolean ins_time(ManagerDTO schedule) {
		String sql = "INSERT INTO MOVIE_SCHEDULE VALUES (SCHEDULE_NO.NEXTVAL,?,?,?,?,?)";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, schedule.in_theater);
			ps.setInt(2, schedule.in_movie);
			ps.setString(3, schedule.in_screen);
			ps.setString(4, schedule.in_time);
			ps.setString(5, schedule.in_end_time);

			result = ps.executeUpdate();
		} catch (SQLException e) {
		}

		return result == 1;

	}

	// 상영시간표 수정하는 메소드
	public boolean up_time(ManagerDTO schedule) {
		int result = 0;
		String sql = "UPDATE MOVIE_SCHEDULE SET SCHEDULE_START = ? , SCHEDULE_END = ?" + "WHERE SCHEDULE_NO = ? ";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, schedule.up_time);
			ps.setString(2, schedule.up_end_time);
			ps.setInt(3, schedule.up_no);

			result = ps.executeUpdate();

		} catch (SQLException e) {
		}

		return result == 1;

	}

	// 상영시간표 삭제하는 메소드
	public boolean del_time(ManagerDTO schedule) {
		int result = 0;
		String sql = "DELETE FROM MOVIE_SCHEDULE WHERE SCHEDULE_NO = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, schedule.del_no);
			result = ps.executeUpdate();
		} catch (SQLException e) {
		}
		// 메소드 생성
		return result == 1;

	}

	// 전체 지점 보여주는 메소드
	public String theater() {
		String sql = "SELECT THEATER_NO,THEATER_NAME FROM THEATER";
		String result = "";

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				result += rs.getInt("THEATER_NO") + ". " + rs.getString("THEATER_NAME") + "\n";
			}
			System.out.println();
		} catch (SQLException e) {
		}
		return result;
	}

	// 영화 전체를 보여주는 메소드
	public String movie() {
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

	// 현재 상영시간표를 보여주는 메소드
	public String schedule(int choice_theater, int choice_movie) {
		String sql = "SELECT SCHEDULE_NO, SCHEDULE_START, SCHEDULE_END "
				+ "FROM MOVIE_SCHEDULE WHERE SCHEDULE_THEATER=? AND SCHEDULE_MOVIE=?";
		String result = "";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, choice_theater);
			ps.setInt(2, choice_movie);
			rs = ps.executeQuery();

			while (rs.next()) {
				result += "고유 번호 : " + rs.getInt("SCHEDULE_NO") + " - 시간 : [" + rs.getString("SCHEDULE_START") + "~"
						+ rs.getString("SCHEDULE_END") + "]\n";
			}
		} catch (SQLException e) {
		}

		return result;
	}

	// 지점 전체를 보여주는 메소드 입니다.
//	public String theater() {
//		String result = "";
//		String sql = "SELECT * FROM THEATER";
//		try {
//			ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				result += rs.getInt("THEATER_NO") + "." + rs.getString("THEATER_NAME") + "\n";
//			}
//		} catch (SQLException e) {
//
//		}
//		return result;
//	}

	// 지점 삭제하는 메소드 입니다.
	public boolean del_theater(int del_theater) {
		int result = 0;
		String sql = "DELETE FROM THEATER WHERE THEATER_NO = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, del_theater);
			result = ps.executeUpdate();
		} catch (SQLException e) {

		}
		return result == 1;
	}

	//지점 추가
	public boolean add_theater(ManagerDTO site) {
		String sql = "INSERT INTO THEATER VALUES (THEATER_NO.NEXTVAL, ?)";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, site.getAdd_theater());
			result = ps.executeUpdate();
		} catch (SQLException e) {

		}
		return result == 1;
	}

	//지점 수정
	public boolean change_theater(int up_theater, String in_theater) {
		String sql = "UPDATE THEATER SET THEATER_NAME = ? WHERE THEATER_NO = ?";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, in_theater);
			ps.setInt(2, up_theater);
			result = ps.executeUpdate();
		} catch (SQLException e) {
		}
		return result == 1;
	}

	//지점 중복 확인
	public boolean check_theater(String add_theater) {
		String sql = "SELECT THEATER_NAME FROM THEATER WHERE THEATER_NAME = ?";
		boolean result = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, add_theater);
			rs = ps.executeQuery();
			if (!rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
		}
		return result;
	}
}
