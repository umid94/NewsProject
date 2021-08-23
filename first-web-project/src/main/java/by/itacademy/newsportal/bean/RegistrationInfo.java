package by.itacademy.newsportal.bean;

import java.io.Serializable;

public class RegistrationInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String email;
	private String login;
	private String password;
	private String role = "user";
	
	public RegistrationInfo() {}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

	public String getRole() {
		return role;
	}
	
	@Override
	public int hashCode() {
		  final int prime = 31;
		   int result = 1;
		   result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());  
		   result = prime * result + ((lastName == null) ? 0 : lastName.hashCode()); 
		   result = prime * result + ((email == null) ? 0 : email.hashCode()); 
		   result = prime * result + ((role == null) ? 0 : role.hashCode()); 
		   result = prime * result + ((login == null) ? 0 : login.hashCode()); 
		   result = prime * result + ((password == null) ? 0 : password.hashCode()); 
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

	    RegistrationInfo guest = (RegistrationInfo) obj;
	        return (firstName == guest.firstName || (firstName != null && firstName.equals(guest.getFirstName()))) 
	        && (lastName == guest.lastName || (lastName != null && lastName.equals(guest.getLastName())))
	    	&& (email == guest.email || (email != null && email.equals(guest.getEmail())))
	    	&& (role == guest.role || (role != null && role.equals(guest.getRole())))
	    	&& (login == guest.login || (login != null && login.equals(guest.getLogin())))
		    && (password == guest.password || (password != null && password.equals(guest.getPassword())));
	    
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", login="
				+ login + ", password=" + password + ", role=" + role;
	}
	
	
}
