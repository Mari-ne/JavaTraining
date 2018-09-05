package entity;

public class User extends Entity {

	private String login;
	private String password;
	private String e_mail;
	private String role;
	
	public User() {}
	
	public User(String login, String password, String e_mail, String role) {
		this.login = login;
		this.password = password;
		this.e_mail = e_mail;
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password 
				+ ", e_mail=" + e_mail + ", role=" + role + "]";
	}
	
	
	
}
