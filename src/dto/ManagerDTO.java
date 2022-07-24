package dto;

public class ManagerDTO {
	public int in_theater;
	public int in_movie;
	public String in_screen;
	public String in_time;
	public String in_end_time;
	public int up_no;
	public String up_time;
	public String up_end_time;
	public int del_no;
	private String add_theater;

	public ManagerDTO() {
	}

	public ManagerDTO(int in_theater, int in_movie, String in_screen, String in_time, String in_end_time, int up_no,
			String up_time, String up_end_time, int del_no) {
		this.in_theater = in_theater;
		this.in_movie = in_movie;
		this.in_screen = in_screen;
		this.in_time = in_time;
		this.in_end_time = in_end_time;
		this.up_no = up_no;
		this.up_time = up_time;
		this.up_end_time = up_end_time;
		this.del_no = del_no;
	}

	public ManagerDTO(String add_theater) {
		this.add_theater = add_theater;

	}

	public String getAdd_theater() {
		return add_theater;
	}

	public void setAdd_theater(String add_theater) {
		this.add_theater = add_theater;
	}

}
