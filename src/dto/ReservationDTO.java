package dto;

public class ReservationDTO {
	
	private int reserve_no, reserve_people, reserve_price;
	private String reserve_user, reserve_theater, reserve_movie, reserve_screen, reserve_date;
	private String schedule_start, schedule_end, reserve_seat, reserve_time, reserve_status;
	
	public ReservationDTO() {}

	public ReservationDTO(String reserve_user, String reserve_theater, String reserve_date, String reserve_movie, String reserve_screen,
			String schedule_start, String schedule_end, String reserve_seat, int reserve_people, int reserve_price) {
		this.reserve_user = reserve_user;
		this.reserve_theater = reserve_theater;
		this.reserve_date = reserve_date;
		this.reserve_movie = reserve_movie;
		this.reserve_screen = reserve_screen;
		this.schedule_start = schedule_start;
		this.schedule_end = schedule_end;
		this.reserve_seat = reserve_seat;
		this.reserve_people = reserve_people;
		this.reserve_price = reserve_price;
	}

	public int getReserve_no() {
		return reserve_no;
	}

	public void setReserve_no(int reserve_no) {
		this.reserve_no = reserve_no;
	}

	public int getReserve_people() {
		return reserve_people;
	}

	public void setReserve_people(int reserve_people) {
		this.reserve_people = reserve_people;
	}

	public int getReserve_price() {
		return reserve_price;
	}

	public void setReserve_price(int reserve_price) {
		this.reserve_price = reserve_price;
	}

	public String getReserve_user() {
		return reserve_user;
	}

	public void setReserve_user(String reserve_user) {
		this.reserve_user = reserve_user;
	}

	public String getReserve_theater() {
		return reserve_theater;
	}

	public void setReserve_theater(String reserve_theater) {
		this.reserve_theater = reserve_theater;
	}

	public String getReserve_movie() {
		return reserve_movie;
	}

	public void setReserve_movie(String reserve_movie) {
		this.reserve_movie = reserve_movie;
	}

	public String getReserve_screen() {
		return reserve_screen;
	}

	public void setReserve_screen(String reserve_screen) {
		this.reserve_screen = reserve_screen;
	}

	public String getReserve_date() {
		return reserve_date;
	}

	public void setReserve_date(String reserve_date) {
		this.reserve_date = reserve_date;
	}

	public String getSchedule_start() {
		return schedule_start;
	}

	public void setSchedule_start(String schedule_start) {
		this.schedule_start = schedule_start;
	}

	public String getSchedule_end() {
		return schedule_end;
	}

	public void setSchedule_end(String schedule_end) {
		this.schedule_end = schedule_end;
	}

	public String getReserve_seat() {
		return reserve_seat;
	}

	public void setReserve_seat(String reserve_seat) {
		this.reserve_seat = reserve_seat;
	}

	public String getReserve_time() {
		return reserve_time;
	}

	public void setReserve_time(String reserve_time) {
		this.reserve_time = reserve_time;
	}

	public String getReserve_status() {
		return reserve_status;
	}

	public void setReserve_status(String reserve_status) {
		this.reserve_status = reserve_status;
	}
	
	
	
}