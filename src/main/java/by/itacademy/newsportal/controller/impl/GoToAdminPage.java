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

public class GoToAdminPage implements Command{
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String ADMIN_PAGE = "/WEB-INF/jsp/adminpage.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "go_to_admin_page";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("Controller?command=go_to_authorization_page");
			return;
		}
		User user = (User)session.getAttribute("user");
		if(user == null) {
			response.sendRedirect("Controller?command=go_to_authorization_page");
			return;
		}
		if(! "admin".equals(user.getRole())) {
			session.removeAttribute("user");
			response.sendRedirect("Controller?command=go_to_authorization_page");
			return;
		}
		
		double count = 0;
		List<News> newses = new ArrayList<News>();
		try {
			 count = NEWSSERVICE.getCountNews();
			 newses = NEWSSERVICE.getAllPagNews(0);
		}catch(ServiceException e) {
			
		}
		
		count =(Math.ceil(count / 5));
		request.setAttribute("countNews", count);
		request.setAttribute("newses", newses);
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PAGE);
		requestDispatcher.forward(request, response);

	}

}
