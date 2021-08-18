package by.itacademy.newsportal.controller.impl;

import java.io.IOException;

import by.itacademy.newsportal.bean.RegistrationInfo;
import by.itacademy.newsportal.controller.Command;
import by.itacademy.newsportal.service.ServiceException;
import by.itacademy.newsportal.service.ServiceProvider;
import by.itacademy.newsportal.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationNewUser implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final UserService userService = provider.getUserService();
	
	 
	private static final String SENDRED_TRY = "Controller?command=go_to_authorization_page&message=taram taram pam pam pozdravlyayu avtorizuysya";
	private static final String SENDRED_CATCH = "Controller?command=go_to_registration_page&message=pojaluysta poprobuyte eshe raz ";
    private static final String NAME = "firstname";
    private static final String SURNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isTrue = false;
	    
	    try{
	    	userService.validateDuplicate(LOGIN, EMAIL);
	    } catch(ServiceException e){
			isTrue = true;
	    }
     	   RegistrationInfo info = new RegistrationInfo();
     	   info.setFirstName(NAME);
		   info.setLastName(SURNAME);
		   info.setEmail(EMAIL);
	       info.setLogin(LOGIN);
		   info.setPassword(PASSWORD);
		
				
		try {
			
			userService.registration(info) ;
			
			response.sendRedirect(SENDRED_TRY);		
			 
		} catch (ServiceException e) {
			if(isTrue) {
				response.sendRedirect(SENDRED_CATCH + "uje sushestvuet takoy login ili email urod");
			}else {
			  response.sendRedirect(SENDRED_CATCH);
			}
			
		}      
}
//	 private String isValid(HttpServletRequest request) {
//		 
//		  String firstName = request.getParameter("firstname");
//		  String lastName = request.getParameter("lastname");
//		  String email = request.getParameter("email");
//	      String login = request.getParameter("login");
//		  String password = request.getParameter("password");
//		  
//		  String isValid = ""; 
//		  
//		  if(firstName == null || firstName.isEmpty()) {
//			isValid = "Firstame fields must not be empty";  
//		  }
//		  if(lastName == null || lastName.isEmpty()) {
//				isValid += "\nLastname fields must not be empty";  
//		  }
//		  if(email == null || email.isEmpty()) {
//				isValid += "\nemail fields must not be empty";  
//		  }
//		  if(login == null || login.isEmpty()) {
//				isValid += "\nlogin fields must not be empty";  
//		  }
//		  if(password == null || password.isEmpty()) {
//				isValid += "\npassword fields must not be empty";  
//		  }	  
//	  	  return isValid;
//		 
//	 }
}
