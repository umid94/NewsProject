package by.itacademy.newsportal.bean;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String role;
	private String login;

	public User(int id, String login, String role) {
		super();
		this.id = id;
		this.role = role;
		this.login = login;
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public int hashCode() {
		  final int prime = 31;
		   int result = 1;
		   result = prime * result + id;
		   result = prime * result + ((role == null) ? 0 : role.hashCode());  
		   result = prime * result + ((login == null) ? 0 : login.hashCode()); 
		   return result;	
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
	        return true;
	    }
	    if (obj == null || obj.getClass() != this.getClass()) {
	        return false;
	    }

	    User guest = (User) obj;
	        return id == guest.id && (role == guest.role || (role != null && role.equals(guest.getRole()))) 
	        && (login == guest.login || (login != null && login.equals(guest.getLogin())));
	    
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + ", id=" + id + ", role=" + role + ", login=" + login;
	}
}
