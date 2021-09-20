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

public class GoToMainPage implements Command{
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "Go_To_Main_Page";
    public static final String ERROR_GET_NEWS_REDIR ="Controller?command=go_to_main_page&message=Proizoshlo oshibka pri pokaze novostey poprobuyte obnovit stranitsu";
	
	private final static Logger log = LogManager.getLogger("mylogger");

	@Override 
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<News> newses = new ArrayList<News>();
		try {
			newses = NEWSSERVICE.getLastNews();
			request.setAttribute("newses", newses);
		}catch(ServiceException e) {
			
			log.error("error when loading the latest news in the main page", e);
			response.sendRedirect(ERROR_GET_NEWS_REDIR);
			return;
		}
				
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(MAIN_PAGE);
		requestDispatcher.forward(request, response);
		
	}

}
