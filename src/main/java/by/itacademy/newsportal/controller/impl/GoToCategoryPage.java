package by.itacademy.newsportal.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.itacademy.newsportal.bean.News;
import by.itacademy.newsportal.controller.Command;
import by.itacademy.newsportal.service.NewsService;
import by.itacademy.newsportal.service.ServiceException;
import by.itacademy.newsportal.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToCategoryPage implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String CATEGORY_PAGE = "/WEB-INF/jsp/categorynewspage.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "go_to_category_page";
	public static final String CATEGORY_NAME = "category";
	public static final String ERRMESSAGE = "oshibka pri poluchenie novostey category";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter(CATEGORY_NAME);
		List<News> categNews = new ArrayList<>();
		
		try {
			categNews = NEWSSERVICE.getCategNews(category);
			
			request.setAttribute("categNews", categNews);
		} catch (ServiceException e) {
			
			request.setAttribute("errMessage", ERRMESSAGE);	
		}
		
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(CATEGORY_PAGE);

		requestDispatcher.forward(request, response);

		
	}

}
