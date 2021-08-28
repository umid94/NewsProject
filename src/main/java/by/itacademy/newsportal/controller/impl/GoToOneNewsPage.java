package by.itacademy.newsportal.controller.impl;

import java.io.IOException;

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

public class GoToOneNewsPage implements Command{
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String ONENEWS_PAGE = "/WEB-INF/jsp/onenewspage.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "go_to_one_news_page&id_news=";
	public static final String ID_NEWS = "id_news";
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
		
		News news = null;
		int id = 0;
		if(request.getParameter(ID_NEWS) != null) {
		 id = Integer.parseInt(request.getParameter(ID_NEWS));
		}
		try {
			news = NEWSSERVICE.getOneNews(id);
		} catch (ServiceException e) {
			
		}
		request.setAttribute("news", news);
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND + id);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ONENEWS_PAGE);

		requestDispatcher.forward(request, response);
		
	}

}
