package dto;

import java.util.HashMap;

public class FoodDTO {
	private int food_no;
	private String food_name;
	private int food_price;
	private HashMap<String, Integer> food_info;
	
	
	public FoodDTO() {}
	
	public FoodDTO(int food_no, String food_name, int food_price) {
		this.food_no = food_no;
		this.food_name = food_name;
		this.food_price = food_price;
	}
	
	public FoodDTO(HashMap<String, Integer> foods, int food_price) {
		this.food_info = foods;
		this.food_price = food_price;
	}
	
	
	public HashMap<String, Integer> getFood_info() {
		return food_info;
	}

	public void setFood_info(HashMap<String, Integer> food_info) {
		this.food_info = food_info;
	}

	public int getFood_no() {
		return food_no;
	}

	public void setFood_no(int food_no) {
		this.food_no = food_no;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public int getFood_price() {
		return food_price;
	}

	public void setFood_price(int food_price) {
		this.food_price = food_price;
	}

	
	
}
