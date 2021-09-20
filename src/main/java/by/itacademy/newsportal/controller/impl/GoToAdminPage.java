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
import jakarta.servlet.http.HttpSession;

public class GoToAdminPage implements Command{
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String ADMIN_PAGE = "/WEB-INF/jsp/adminpage.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "go_to_admin_page";
	public static final String SENDRED_SESSION = "Controller?command=go_to_authorization_page";
	public static final String AdminMess = "Proizoshla oshibka pri polucheniye novostey esli ty admin to znaesh kak postupit :)";
	
	private final static Logger log = LogManager.getLogger("mylogger");

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
		
		if(!"admin".equals(user.getRole())) {
			log.warn("User pytaetsya popast v adminku");
			session.removeAttribute("user");
			response.sendRedirect(SENDRED_SESSION);
			return;
		}
		
		double count = 0;
		List<News> newses = new ArrayList<News>();
		try {
			 count = NEWSSERVICE.getCountNews();
			 newses = NEWSSERVICE.getAllPagNews(0);
		}catch(ServiceException e) {
			log.error("error when entering admin page", e);
			request.setAttribute("adminMess", AdminMess);
			request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PAGE);
			requestDispatcher.forward(request, response);
			return;
		}
		
		count =(Math.ceil(count / 5));
		request.setAttribute("countNews", count);
		request.setAttribute("newses", newses);
		request.getSession(true).setAttribute(SESSION_ATTR_PATH, SESSION_ATTR_LOCAL_COMMAND);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PAGE);
		requestDispatcher.forward(request, response);

	}

}
