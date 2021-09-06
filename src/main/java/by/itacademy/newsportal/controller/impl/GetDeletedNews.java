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

public class GetDeletedNews implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String DELETED_NEWS_PAGE = "/WEB-INF/jsp/disapprovednews.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "get_deleted_news";
	 public static final String MESSAGE = "Oshibka pri prochetenie udalennix novostey kak ty pisal kod :)";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		List<News> news = new ArrayList<>();
		
		try {
			news = NEWSSERVICE.getDeletedNews();
			request.setAttribute("news", news);
		} catch (ServiceException e) {
			request.setAttribute("message", MESSAGE);
			request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(DELETED_NEWS_PAGE);
			requestDispatcher.forward(request, response);
			return;
		}
		 
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(DELETED_NEWS_PAGE);
		requestDispatcher.forward(request, response);
	}
}
