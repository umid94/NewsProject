package by.itacademy.newsportal.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String role;
	private String login;

	public User(String login, String role) {
		super();
		this.role = role;
		this.login = login;
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
	        return (role == guest.role || (role != null && role.equals(guest.getRole()))) 
	        && (login == guest.login || (login != null && login.equals(guest.getLogin())));
	    
	}

	@Override
	public String toString() {
		return "User [role=" + role + ", login=" + login + "]";
	}
	
	
	
	
	
    
	
}
