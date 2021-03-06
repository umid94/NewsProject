package by.itacademy.newsportal.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.itacademy.newsportal.bean.News;
import by.itacademy.newsportal.controller.Command;
import by.itacademy.newsportal.service.NewsService;
import by.itacademy.newsportal.service.ServiceException;
import by.itacademy.newsportal.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetDisapprovedNews implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String DISAPPROVED_NEWS_PAGE = "/WEB-INF/jsp/disapprovednews.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "get_disapproved_news";
    public static final String MESSAGE = "Oshibka pri prochetenie ne odobrennix novostey kak ty pisal kod :)";
    
	private final static Logger log = LogManager.getLogger("mylogger");

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		List<News> news = new ArrayList<>();
		
		try {
			news = NEWSSERVICE.getDisApprovedNews();
			request.setAttribute("news", news);
		} catch (ServiceException e) {
			log.error("Error while retrieving disapproved news", e);
			request.setAttribute("message", MESSAGE);
			request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(DISAPPROVED_NEWS_PAGE);
			requestDispatcher.forward(request, response);
			return;
		}
		
		 
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(DISAPPROVED_NEWS_PAGE);
		requestDispatcher.forward(request, response);
	}

}
