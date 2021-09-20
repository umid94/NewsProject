package by.itacademy.newsportal.controller.impl;

import java.io.IOException;

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

public class GoToOneNewsPage implements Command{
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String ONENEWS_PAGE = "/WEB-INF/jsp/onenewspage.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "go_to_one_news_page&id_news=";
	public static final String ID_NEWS = "id_news";
	public static final String ERR_REDIRECT = "Controller?command=go_to_profile_user_page&errMessage=oshibka pri prochetenie novostya";
    
	private final static Logger log = LogManager.getLogger("mylogger");

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		News news = null;
		int id = 0;
		if(request.getParameter(ID_NEWS) != null) {
		 id = Integer.parseInt(request.getParameter(ID_NEWS));
		}
		try {
			news = NEWSSERVICE.getOneNews(id);
		} catch (ServiceException e) {
			log.error("error while reading one news", e);
			response.sendRedirect(ERR_REDIRECT);
			return;
		}
		request.setAttribute("news", news);
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND + id);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ONENEWS_PAGE);

		requestDispatcher.forward(request, response);
		
	}

}
