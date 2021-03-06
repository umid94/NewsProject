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

public class GoToCategoryPage implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String PROFILE_PAGE = "/WEB-INF/jsp/profileuser.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "go_to_category_page&category=";
	public static final String CATEGORY_NAME = "category";
	public static final String ERR_REDIRECT = "Controller?command=go_to_profile_user_page&errMessage=oshibka pri poluchenie novostey category";

	private final static Logger log = LogManager.getLogger("mylogger");

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter(CATEGORY_NAME);
		List<News> categNews = new ArrayList<>();
		
		try {
			categNews = NEWSSERVICE.getCategNews(category);
			request.setAttribute("newses", categNews);
		} catch (ServiceException e) {
			log.error("error while reading category news", e);
			response.sendRedirect(ERR_REDIRECT);
		    return;
		}
		
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND + category);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PROFILE_PAGE);

		requestDispatcher.forward(request, response);

		
	}

}
