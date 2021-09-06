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
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToProfileUserPage implements Command {
    
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String PROFILEUSER_PAGE = "/WEB-INF/jsp/profileuser.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "go_to_profile_user_page";
	private static final String SEND_SESSION_="Controller?command=go_to_authorization_page";
	private static final String USER_MESS_="Proizoshla oshibka pri pokaze novostey poprobuyte obnovit stranisu";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(SEND_SESSION_);
			return;
		}
		User user = (User)session.getAttribute("user");
		if(user == null) {
			response.sendRedirect(SEND_SESSION_);
			return;
		}
		double count = 0;
		List<News> newses = new ArrayList<News>();
		try {
			 count = NEWSSERVICE.getCountNews();
			 newses = NEWSSERVICE.getAllPagNews(0);
		}catch(ServiceException e) {
			request.setAttribute("userMess", USER_MESS_);
			request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PROFILEUSER_PAGE);
			requestDispatcher.forward(request, response);
			return;
		}
		
		count =(Math.ceil(count / 5));
		request.setAttribute("countNews", count);
		request.setAttribute("newses", newses);
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PROFILEUSER_PAGE);

		requestDispatcher.forward(request, response);

	}

}
