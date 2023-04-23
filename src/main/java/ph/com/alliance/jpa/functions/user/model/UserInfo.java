package ph.com.alliance.jpa.functions.user.model;


public class UserInfo {
    private int userID;
    private String email;
    private String firstname;
    private String lastname;
    private String contact_number;
    private String user_type;
    private String createdAt;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getcontact_number() {
		return contact_number;
	}
	public void setcontact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getuser_type() {
		return user_type;
	}
	public void setuser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String date) {
		this.createdAt = date;
	}

    
    
    
}
