package by.itacademy.newsportal.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.itacademy.newsportal.bean.News;
import by.itacademy.newsportal.bean.User;
import by.itacademy.newsportal.controller.Command;
import by.itacademy.newsportal.service.NewsService;
import by.itacademy.newsportal.service.ServiceException;
import by.itacademy.newsportal.service.ServiceProvider;
import by.itacademy.newsportal.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignIn implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final UserService USERSERVICE = provider.getUserService();
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String SENDRED_TRY_USER = "Controller?command=go_to_profile_user_page&message=Welcome to your page in Breaking News Bro :)";
	private static final String SENDRED_TRY_ADMIN = "Controller?command=go_to_admin_page&message=Welcome to your page in Breaking News Bro Admin :)";
	private static final String SENDRED_CATCH = "Controller?command=go_to_authorization_page&message=polzovatel pojalustya vvedi zanovo pravilniy login i parol";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getParameter(LOGIN);
		String password = (String) request.getParameter(PASSWORD);

		try {
			User user = USERSERVICE.authorizationUser(login, password);
			
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			if ("admin".equals(user.getRole())) {
				response.sendRedirect(SENDRED_TRY_ADMIN);

			} else {
				response.sendRedirect(SENDRED_TRY_USER);
			}
		} catch (ServiceException e) {
			response.sendRedirect(SENDRED_CATCH);
		}
		
	}

}
