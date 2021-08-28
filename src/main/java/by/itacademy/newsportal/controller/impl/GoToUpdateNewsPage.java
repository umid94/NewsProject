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

public class GoToUpdateNewsPage implements Command{
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String UPDATE_NEWS_PAGE = "/WEB-INF/jsp/updatenews.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "go_to_update_news_page";
    public static final String IDNEWS = "id_news";
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
		int id = Integer.parseInt(request.getParameter(IDNEWS));
		News news =  null;
				
				try {
					news = NEWSSERVICE.getOneNews(id);
				} catch (ServiceException e) {
					e.printStackTrace();
				}
		request.setAttribute("news", news);
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(UPDATE_NEWS_PAGE);

		requestDispatcher.forward(request, response);

	}

}
