package by.itacademy.newsportal.controller.impl;

import java.io.IOException;

import by.itacademy.newsportal.bean.News;
import by.itacademy.newsportal.bean.User;
import by.itacademy.newsportal.controller.Command;
import by.itacademy.newsportal.service.NewsService;
import by.itacademy.newsportal.service.ServiceException;
import by.itacademy.newsportal.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddNews implements Command{
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String TITLE = "title";
	public static final String BRIEF = "brief";
	public static final String CONTENT = "content";
	public static final String CATEGORY = "category";
	public static final String IDUSER = "id_user";
	private static final String SEND_SESSION_="Controller?command=go_to_authorization_page";
	private static final String SENDRED_TRY_USER = "Controller?command=go_to_profile_user_page&message=Your News succesfully sent for approval :)";
	private static final String SENRED_TRY_ADMIN =  "Controller?command=go_to_admin_page&message=News succesfully added";
	private static final String SENDRED_CATCH = "Controller?command=go_to_addnews_page&message=Add new News failed please try again";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(SEND_SESSION_);
			return;
		}
		User user = (User)session.getAttribute("user");
		if(user == null) {
			response.sendRedirect(SEND_SESSION_);
			return;
		}
		
		String title = request.getParameter(TITLE);
		String brief = request.getParameter(BRIEF);
		String content = request.getParameter(CONTENT);
		String category = request.getParameter(CATEGORY);
        int idUser = Integer.parseInt(request.getParameter(IDUSER));
        
		News news = new News(title, brief, content, category, idUser);
		if("admin".equals(user.getRole())) {
			news.setStatus("approved");
		}else {
			news.setStatus("disapproved");
		}
		
		try {
			NEWSSERVICE.add(news);
			if("admin".equals(user.getRole())) {
			response.sendRedirect(SENRED_TRY_ADMIN);
			}else {
			  response.sendRedirect(SENDRED_TRY_USER);
			}
		} catch (ServiceException e) {
			response.sendRedirect(SENDRED_CATCH);
		}
	}
}
