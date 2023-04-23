package ph.com.alliance.jpa.functions.usertest.model;

import ph.com.alliance.jpa.entity.UserTest;

public class User {
    
	private int userId;

	private String name;

	private String password;
		
	public User() {
	}
	
	public User(UserTest u) {
        this.setUserId(u.getUserId());
        this.setName(u.getName());
        this.setPassword(u.getPassword());
    }
    
    public UserTest toEntity() {
        UserTest u = new UserTest();
        u.setUserId(this.getUserId());
        u.setName(this.getName());
        u.setPassword(this.getPassword());
        return u;
    }

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}