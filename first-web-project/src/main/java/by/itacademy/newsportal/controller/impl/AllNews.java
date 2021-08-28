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

public class AllNews implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	private static final String PROFILEUSER_PAGE = "/WEB-INF/jsp/profileuser.jsp";
	private static final String ADMIN_PAGE = "/WEB-INF/jsp/adminpage.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "all_news";
	public static final String PAGE = "page";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page = 0;
		double count = 0;
		List<News> newses = new ArrayList<News>();
		if(request.getParameter(PAGE) != null) {
		page = Integer.parseInt(request.getParameter(PAGE));
		}
		try {
			 count = NEWSSERVICE.getCountNews();
			 newses = NEWSSERVICE.getAllPagNews((page - 1) * 5);
		}catch(ServiceException e) {
			
		}
		
		count =(Math.ceil(count / 5));
		User user = (User)request.getSession().getAttribute("user");
		if(!"admin".equals(user.getRole())) {
		request.setAttribute("countNews", count);
		request.setAttribute("newses", newses);
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND + "&page=" + page);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PROFILEUSER_PAGE);

		requestDispatcher.forward(request, response);
		}else {
			request.setAttribute("countNews", count);
			request.setAttribute("newses", newses);
			request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND + "&page=" + page);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PAGE);

			requestDispatcher.forward(request, response);
		}
	}

}
