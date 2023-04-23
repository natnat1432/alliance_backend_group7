package ph.com.alliance.jpa.functions.user.model;

import ph.com.alliance.jpa.entity.UserEntity;


public class UserModel {
	
	Integer userID;
	String email;
	String username;
	String password;
	String firstname;
	String lastname;
	String contact_number;
	String user_type;
	String created_at;

	
	public UserModel() {
	}
	
	public UserModel(UserEntity u) {
        this.setUserID(u.getUserID());
        this.setEmail(u.getEmail());
        this.setPassword(u.getPassword());
        this.setFirstname(u.getFirstname());
        this.setLastname(u.getLastname());
        this.setcontact_number(u.getcontact_number());
        this.setuser_type(u.getuser_type());
        this.setCreated_at(u.getCreated_at());
    
      
    }
	


	public UserEntity toEntity() {
		   UserEntity u = new UserEntity();
	       	u.setUserID(this.getUserID());
	       	u.setEmail(this.getEmail());
	       	u.setPassword(this.getPassword());
	       	u.setFirstname(this.getFirstname());
	       	u.setLastname(this.getLastname());
	       	u.setcontact_number(this.getcontact_number());
	       	u.setuser_type(this.getuser_type());
	       	u.setCreated_at(this.getCreated_at());
	       
	        return u;
	    }

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	
	

	
	
	
}
