package dto;

public class MovieDTO {
	private int movie_no;
	private String movie_name;
	private String movie_genre;
	private int movie_age;
	private String movie_country;
	private String movie_release;
	private String movie_runningtime;
	private int movie_viewers;
	private int movie_rating;
	
	public MovieDTO() {}

	public MovieDTO(int movie_no, String movie_name, String movie_genre, int movie_age, String movie_country,
			String movie_release, String movie_runningtime, int movie_viewers, int movie_rating) {
		super();
		this.movie_no = movie_no;
		this.movie_name = movie_name;
		this.movie_genre = movie_genre;
		this.movie_age = movie_age;
		this.movie_country = movie_country;
		this.movie_release = movie_release;
		this.movie_runningtime = movie_runningtime;
		this.movie_viewers = movie_viewers;
		this.movie_rating = movie_rating;
	}



	public int getMovie_no() {
		return movie_no;
	}

	void setMovie_no(int movie_no) {
		this.movie_no = movie_no;
	}

	public String getMovie_name() {
		return movie_name;
	}

	void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	
	public String getMovie_genre() {
		return movie_genre;
	}
	
	void setMovie_genre(String movie_genre) {
		this.movie_genre = movie_genre;
	}

	public int getMovie_age() {
		return movie_age;
	}

	void setMovie_age(int movie_age) {
		this.movie_age = movie_age;
	}

	public String getMovie_country() {
		return movie_country;
	}

	void setMovie_country(String movie_country) {
		this.movie_country = movie_country;
	}

	public String getMovie_release() {
		return movie_release;
	}

	void setMovie_release(String movie_release) {
		this.movie_release = movie_release;
	}

	public String getMovie_runningtime() {
		return movie_runningtime;
	}

	void setMovie_runningtime(String movie_runningtime) {
		this.movie_runningtime = movie_runningtime;
	}

	public int getMovie_viewers() {
		return movie_viewers;
	}

	void setMovie_viewers(int movie_viewers) {
		this.movie_viewers = movie_viewers;
	}

	public int getMovie_rating() {
		return movie_rating;
	}

	void setMovie_rating(int movie_rating) {
		this.movie_rating = movie_rating;
	}
}