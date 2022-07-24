package dto;

public class M_MovieDTO {
	private int movie_no;
	private String movie_name;
	private String movie_director;
	private String movie_actor;
	private String movie_genre;
	private int movie_age;
	private String movie_country;
	private String movie_release;
	private String movie_runningtime;
	private int movie_viewers;
	private double movie_rating;
	
	public M_MovieDTO() {
		
	}

	public M_MovieDTO(int movie_no, String movie_name, String movie_director, String movie_actor, String movie_genre,
			int movie_age, String movie_country, String movie_release, String movie_runningtime, int movie_viewers,
			double movie_rating) {
		super();
		this.movie_no = movie_no;
		this.movie_name = movie_name;
		this.movie_director = movie_director;
		this.movie_actor = movie_actor;
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

	public void setMovie_no(int movie_no) {
		this.movie_no = movie_no;
	}
	
	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	
	public String getMovie_director() {
		return movie_director;
	}

	public void setMovie_director(String movie_director) {
		this.movie_director = movie_director;
	}
	
	public String getMovie_actor() {
		return movie_actor;
	}

	public void setMovie_actor(String movie_actor) {
		this.movie_actor = movie_actor;
	}
	
	public String getMovie_genre() {
		return movie_genre;
	}

	public void setMovie_genre(String movie_genre) {
		this.movie_genre = movie_genre;
	}
	
	public int getMovie_age() {
		return movie_age;
	}

	public void setMovie_age(int movie_age) {
		this.movie_age = movie_age;
	}
	
	public String getMovie_country() {
		return movie_country;
	}

	public void setMovie_country(String movie_country) {
		this.movie_country = movie_country;
	}
	
	public String getMovie_release() {
		return movie_release;
	}

	public void setMovie_release(String movie_release) {
		this.movie_release = movie_release;
	}
	
	public String getMovie_runningtime() {
		return movie_runningtime;
	}

	public void setMovie_runningtime(String movie_runningtime) {
		this.movie_runningtime = movie_runningtime;
	}
	
	public int getMovie_viewers() {
		return movie_viewers;
	}

	public void setMovie_viewers(int movie_viewers) {
		this.movie_viewers = movie_viewers;
	}
	
	public double getMovie_rating() {
		return movie_rating;
	}

	public void setMovie_rating(double movie_rating) {
		this.movie_rating = movie_rating;
	}
	
}