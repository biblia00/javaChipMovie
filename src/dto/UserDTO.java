package dto;

//필드(변수)들만 가지고 있음
//변수에 접근하기 위한 getter, setter, 생성자만 존재
public class UserDTO {
	private String userid;
	private String userpw;
	private String username;
	private String usernkname;
	private String birth;
	private String gender;
	private String phagency;
	private String userphone;
	private String useremail;
	private String useraddr;
	private int userpoint;
	private String userlevel;
	private String answer;

	// 기본생성자
	public UserDTO() {
	}
	
	public UserDTO(String userid, String userpw, String username, String usernkname, String birth, String gender,
			String phagency, String userphone, String useremail, String useraddr, int userpoint, String userlevel, String answer) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.usernkname = usernkname;
		this.birth = birth;
		this.gender = gender;
		this.phagency = phagency;
		this.userphone = userphone;
		this.useremail = useremail;
		this.useraddr = useraddr;
		this.userpoint = userpoint;
		this.userlevel = userlevel;
		this.answer = answer;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernkname() {
		return usernkname;
	}

	public void setUsernkname(String usernkname) {
		this.usernkname = usernkname;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhagency() {
		return phagency;
	}

	public void setPhagency(String phagency) {
		this.phagency = phagency;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUsermail() {
		return useremail;
	}

	public void setUsermail(String useremail) {
		this.useremail = useremail;
	}

	public String getUseraddr() {
		return useraddr;
	}

	public void setUseraddr(String useraddr) {
		this.useraddr = useraddr;
	}

	public int getUserpoint() {
		return userpoint;
	}

	public void setUserpoint(int userpoint) {
		this.userpoint = userpoint;
	}

	public String getUserlevel() {
		return userlevel;
	}

	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

//	@Override
//	public String toString() {
//	      return userid+"\t"+userpw+"\t"+username +"\t"+ usernkname +"\t"+ 
//	      birth+"\t"+gender+"\t"+phagency+"\t"+userphone+"\t"+useremail+"\t"+useraddr;
//	   }

}
