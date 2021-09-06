package by.itacademy.newsportal.controller.impl;

import java.io.IOException;

import by.itacademy.newsportal.bean.User;
import by.itacademy.newsportal.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToAddNewsPage implements Command{

	public static final String ADDNEWS_PAGE = "/WEB-INF/jsp/addnews.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "go_to_addnews_page";
	private static final String SEND_SESSION="Controller?command=go_to_authorization_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(SEND_SESSION);
			return;
		}
		User user = (User)session.getAttribute("user");
		if(user == null) {
			response.sendRedirect(SEND_SESSION);
			return;
		}
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADDNEWS_PAGE);

		requestDispatcher.forward(request, response);

	}

}
