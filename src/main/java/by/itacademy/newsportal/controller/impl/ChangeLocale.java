package by.itacademy.newsportal.controller.impl;

import java.io.IOException;

import by.itacademy.newsportal.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeLocale implements Command {
    	
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL = "local";
	public static final String START_OF_PATH = "Controller?command=";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute(SESSION_ATTR_LOCAL, request.getParameter(SESSION_ATTR_LOCAL));
		String path = START_OF_PATH + (String)request.getSession(true).getAttribute(SESSION_ATTR_PATH);
		response.sendRedirect(path);
		
	}

}
