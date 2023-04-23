package ph.com.alliance.jpa.functions.email.model;

public class UserCreationEmailModel {

	private String name;
	
	private String signature;
	
	private String email_credential;
	
	private String password_credential;
	
	private String email_to;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

	public String getEmail_credential() {
		return email_credential;
	}

	public void setEmail_credential(String email_credential) {
		this.email_credential = email_credential;
	}

	public String getPassword_credential() {
		return password_credential;
	}

	public void setPassword_credential(String password_credential) {
		this.password_credential = password_credential;
	}

	public String getEmail_to() {
		return email_to;
	}

	public void setEmail_to(String email_to) {
		this.email_to = email_to;
	}
    
    

}