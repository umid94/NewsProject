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

public class UpdateNews implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService NEWSSERVICE = provider.getNewService();
	public static final String TITLE = "title";
	public static final String BRIEF = "brief";
	public static final String CONTENT = "content";
	public static final String CATEGORY = "category";
	public static final String IDNEWS = "id_news";
	private static final String SENDRED_TRY_ADMIN = "Controller?command=go_to_admin_page&message=Your News succesfully updated";
	private static final String SENDRED_TRY_USER = "Controller?command=go_to_profile_user_page&message=Your News succesfully updated";
	private static final String SENDRED_CATCH = "Controller?command=go_to_addnews_page&message=Update News failed please try again";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = null;
		News news = new News();
		String title = request.getParameter(TITLE);
		String brief = request.getParameter(BRIEF);
		String content = request.getParameter(CONTENT);
		String category = request.getParameter(CATEGORY);
        int idUser = Integer.parseInt(request.getParameter(IDNEWS));
        
        user = (User)request.getSession().getAttribute("user");
		news.setId(idUser);
		news.setTitle(title);
		news.setBrief(brief);
		news.setContent(content);
		news.setCategory(category);
		
		try {
			NEWSSERVICE.update(news);
			if("admin".equals(user.getRole())) {
				response.sendRedirect(SENDRED_TRY_ADMIN);
			}
			if("user".equals(user.getRole())) {
				response.sendRedirect(SENDRED_TRY_USER);
			}
			
		} catch (ServiceException e) {
           response.sendRedirect(SENDRED_CATCH);
    	}
	}

}
