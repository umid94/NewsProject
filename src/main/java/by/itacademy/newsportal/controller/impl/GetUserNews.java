package by.itacademy.newsportal.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

public class GetUserNews implements Command{
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String MYNEWS_PAGE = "/WEB-INF/jsp/mynewspage.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "get_user_news";
	private static final String SEND_RED_CATCH = "Controller?command=go_to_profile_user_page&errMessage=oshibka pri poluchenie polzovatelskix novostey";
	
	private final static Logger log = LogManager.getLogger("mylogger");

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User)request.getSession().getAttribute("user");
		
		int id = user.getId();
		List<News> news = new ArrayList<>();
		
		try {
			news = NEWSSERVICE.getUserNews(id);
		} catch (ServiceException e) {
			log.error("Error receiving user news", e);
			response.sendRedirect(SEND_RED_CATCH);
		    return;
		}
		request.setAttribute("news", news);
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(MYNEWS_PAGE);

		requestDispatcher.forward(request, response);
	}

}
