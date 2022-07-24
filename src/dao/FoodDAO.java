package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import dto.FoodDTO;
import dto.UserDTO;

public class FoodDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	public FoodDAO() {
		conn = DBConnection.getConnection();
	}

	Scanner sc = new Scanner(System.in);

	//푸드 리스트 보여주기
	public String foodList() {
		String sql = "SELECT * FROM FOOD";
		String result = "";
		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery(sql);

			while (rs.next()) {
				result += rs.getInt("FOOD_NO") + ". " + rs.getString("FOOD_NAME") + " " + rs.getInt("FOOD_PRICE") + "원"
						+ "\n";
			}
		} catch (SQLException e) {}
		return result;

	}

	//선택한 번호로 가격 누적
	public ArrayList<String> foodOrder(int select) {
		String sql = "SELECT * FROM FOOD WHERE FOOD_NO = " + Integer.toString(select);
		ArrayList<String>result = new ArrayList<String>();
		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery(sql);
			
			if (rs.next()) {
				result.add(Integer.toString(rs.getInt("FOOD_PRICE"))); 		
				result.add(rs.getString("FOOD_NAME"));						
			}

		} catch (SQLException e) {}
		return result;
	}
	
	//결제
	public void pay(int price_sum) {
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		int point = loginUser.getUserpoint();

		point -= price_sum;
		String sql = "UPDATE USERDATA SET USER_POINT = " + point + " WHERE USER_ID = '" + loginUser.getUserid() + "'";

		int result;
		try {
			ps = conn.prepareStatement(sql);

			result = ps.executeUpdate();

		} catch (SQLException e) {}
		((UserDTO) Session.get("loginUser")).setUserpoint(point);

	}

	//적립
	public boolean save(int price_sum) {
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		int point = loginUser.getUserpoint();

		int save_point = price_sum *= 0.1;
		point += save_point;
		
		String sql = "UPDATE USERDATA SET USER_POINT = " + point + " WHERE USER_ID = '" + loginUser.getUserid() + "'";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {}
		
		((UserDTO) Session.get("loginUser")).setUserpoint(point);
		
		return result == 1;
	}
	
	//푸드오더 테이블에 데이터 추가
	public boolean insertOrder(FoodDTO reserve_food) {
		int result = 0;
		UserDTO loginUser = (UserDTO) Session.get("loginUser");
		String sql = "INSERT INTO FOOD_ORDER VALUES(ORDER_NUM.NEXTVAL,?,?,?,SYSDATE,'주문 완료')";
		FoodDTO fdto = new FoodDTO();
		try {
			ps = conn.prepareStatement(sql);
			
			String tmp = "";
			
			Iterator<String> keys = reserve_food.getFood_info().keySet().iterator();
			while(keys.hasNext()) {
				String strKey = keys.next();
				Integer strValue = reserve_food.getFood_info().get(strKey);
				tmp += strKey + Integer.toString(strValue) + ",";
			}
			
			ps.setString(1, loginUser.getUserid());
			ps.setString(2, tmp);
			ps.setInt(3, reserve_food.getFood_price());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result == 1;
	}
	
	//관리자 푸드 추가
	public boolean insertFood(FoodDTO newfood) {
		String sql = "INSERT INTO FOOD VALUES (FOOD_NO.NEXTVAL,?,?)";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, newfood.getFood_name());
			ps.setInt(2, newfood.getFood_price());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result == 1;
	}

	//관리자 푸드 수정
	public boolean updateFood(int select, String name, int price) {
		String sql = "UPDATE FOOD SET FOOD_NAME = ?, FOOD_PRICE = ? WHERE FOOD_NO = ?";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setInt(3, select);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result == 1;
	}
	
	//관리자 푸드 삭제
	public boolean deleteFood(int select1) {
		String sql = "DELETE FROM FOOD WHERE FOOD_NO = ?";
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














