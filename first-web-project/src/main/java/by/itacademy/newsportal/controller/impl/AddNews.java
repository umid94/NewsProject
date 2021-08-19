package by.itacademy.newsportal.controller.impl;

import java.io.IOException;

import by.itacademy.newsportal.bean.News;
import by.itacademy.newsportal.controller.Command;
import by.itacademy.newsportal.service.NewsService;
import by.itacademy.newsportal.service.ServiceException;
import by.itacademy.newsportal.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddNews implements Command{
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String MAIN_PAGE = "/WEB-INF/jsp/addnews.jsp";
	public static final String SESSION_ATTR_PATH = "path";
	public static final String SESSION_ATTR_LOCAL_COMMAND = "ADDNEWS";
	private static final String SENDRED_TRY = "Controller?command=go_to_admin_page&message=Your News succesfully added :)";
	private static final String SENDRED_CATCH = "Controller?command=addnews&message=Add new News failed please try again";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String brief = request.getParameter("brief");
		String content = request.getParameter("content");
		int id = Integer.parseInt(request.getParameter("user_id"));
		
		News news = new News(title, brief, content, id);
		
		try {
			NEWSSERVICE.add(news);
			response.sendRedirect(SENDRED_TRY);
		} catch (ServiceException e) {
			response.sendRedirect(SENDRED_CATCH);
		}
		
//		HttpSession session = request.getSession(false);
//		if(session == null) {
//			// redirect gotoAuthorizationpage
//			return;
//		}
//		User user = (User)session.getAttribute("user");
//		if(user == null) {
//			// redirect authorizationpage
//			return;
//		}
//		if(! "admin".equals(user.getRole())) {
//			session.removeAttribute("user");
//			// redirect authorizatinpage
//			return;
//		}
		
	}

}
