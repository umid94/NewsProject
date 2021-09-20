package by.itacademy.newsportal.controller.impl;

import java.io.IOException;

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
import jakarta.servlet.http.HttpSession;

public class GoToUpdateNewsPage implements Command{
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String UPDATE_NEWS_PAGE = "/WEB-INF/jsp/updatenews.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "go_to_update_news_page";
    public static final String IDNEWS = "id_news";
    private static final String SEND_CATCH ="Controller?command=go_to_profile_user_page&message=izvinite oshibka pri chteniye novostya";
    private static final String SEND_SESSION_="Controller?command=go_to_authorization_page";
	
	private final static Logger log = LogManager.getLogger("mylogger");

    @Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		if (!"admin".equals(user.getRole())) {
			session.removeAttribute("user");
			response.sendRedirect(SEND_SESSION_);
			return;
		}
		int id = 0;
		if (request.getParameter("id_news") != null) {
			id = Integer.parseInt(request.getParameter(IDNEWS));
		}
		News news = null;

		try {
			news = NEWSSERVICE.getOneNews(id);
		} catch (ServiceException e) {
			log.error("error when updating news", e);
			response.sendRedirect(SEND_CATCH);
			return;
		}
		request.setAttribute("news", news);
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(UPDATE_NEWS_PAGE);

		requestDispatcher.forward(request, response);

	}

}
