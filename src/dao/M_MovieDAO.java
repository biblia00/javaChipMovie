package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import dto.M_MovieDTO;

public class M_MovieDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public M_MovieDAO() {
		conn = DBConnection.getConnection();
		
	}
	
	Scanner sc = new Scanner(System.in);
	
	//영화(전체 정보) 리스트 보여주기
//	public String movieList() {
//		String sql = "SELECT * FROM MOVIE";
//		String result = "";
//		
//		try {
//			ps = conn.prepareStatement(sql);
//			
//			rs = ps.executeQuery(sql);
//			while (rs.next()) {
//				result += rs.getInt("MOVIE_NO") + ". " + rs.getString("MOVIE_NAME") + " " + rs.getString("MOVIE_DIRECTOR") + " " + rs.getString("MOVIE_ACTOR") + " " 
//			+ rs.getString("MOVIE_GENRE") + " " + rs.getInt("MOVIE_AGE") + " " + rs.getString("MOVIE_COUNTRY") + " " + rs.getString("MOVIE_RELEASE") + " " 
//			+ rs.getString("MOVIE_RUNNINGTIME") + " " + rs.getInt("MOVIE_VIEWERS") + " " + rs.getDouble("MOVIE_RATING") + "\n";
//			}
//		} catch (SQLException e) {
//		}
//		return result;
//		
//	}
	
	//영화(번호, 이름) 리스트 보여주기
	public String movieList() {
		String sql = "SELECT MOVIE_NO, MOVIE_NAME FROM MOVIE";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				result += rs.getInt("MOVIE_NO") + ". " + rs.getString("MOVIE_NAME") + "\n";
			}
		} catch (SQLException e) {
		}
		return result;
	}
	
	//관리자 영화 추가
	public boolean insertMovie(M_MovieDTO newmovie) {
		String sql = "INSERT INTO MOVIE VALUES (MOVIE_NO.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, newmovie.getMovie_name());
			ps.setString(2, newmovie.getMovie_director());
			ps.setString(3, newmovie.getMovie_actor());
			ps.setString(4, newmovie.getMovie_genre());
			ps.setInt(5, newmovie.getMovie_age());
			ps.setString(6, newmovie.getMovie_country());
			ps.setString(7, newmovie.getMovie_release());
			ps.setString(8, newmovie.getMovie_runningtime());
			ps.setInt(9, newmovie.getMovie_viewers());
			ps.setDouble(10, newmovie.getMovie_rating());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result == 1;
	}
	
	//관리자 영화 수정
	public boolean updateMovie(int select, String name, String director, String actor, String genre, int  age, String country, String release, String runningtime, int viewers, Double rating) {
		String sql = "UPDATE MOVIE SET MOVIE_NAME =?, MOVIE_DIRECTOR =?, MOVIE_ACTOR =?, MOVIE_GENRE =?, MOVIE_AGE =?, MOVIE_COUNTRY =?, MOVIE_RELEASE =?, MOVIE_RUNNINGTIME =?, MOVIE_VIEWERS =?, MOVIE_RATING =? WHERE MOVIE_NO =?";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
		
			ps.setString(1, name);
			ps.setString(2, director);
			ps.setString(3, actor);
			ps.setString(4, genre);
			ps.setInt(5, age);
			ps.setString(6, country);
			ps.setString(7, release);
			ps.setString(8, runningtime);
			ps.setInt(9, viewers);
			ps.setDouble(10, rating);
			ps.setInt(11, select);
		
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result == 1;
	}
	
	//관리자 영화 삭제
	public boolean deleteMovie(int select1) {
		String sql = "DELETE FROM MOVIE WHERE MOVIE_NO = ?";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, select1);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result == 1;
		
	}
}