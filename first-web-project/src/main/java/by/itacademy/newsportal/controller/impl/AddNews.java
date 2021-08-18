package by.itacademy.newsportal.controller.impl;

import java.io.IOException;

import by.itacademy.newsportal.bean.User;
import by.itacademy.newsportal.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddNews implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			// redirect gotoAuthorizationpage
			return;
		}
		User user = (User)session.getAttribute("user");
		if(user == null) {
			// redirect authorizationpage
			return;
		}
		if(! "admin".equals(user.getRole())) {
			session.removeAttribute("user");
		    
			// redirect authorizatinpage
			return;
		}
		
	}

}
