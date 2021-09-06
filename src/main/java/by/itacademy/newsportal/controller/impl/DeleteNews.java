package by.itacademy.newsportal.controller.impl;

import java.io.IOException;

import by.itacademy.newsportal.bean.User;
import by.itacademy.newsportal.controller.Command;
import by.itacademy.newsportal.service.NewsService;
import by.itacademy.newsportal.service.ServiceException;
import by.itacademy.newsportal.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteNews implements Command {
    private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final NewsService NEWSSERVICE = provider.getNewService();
    private static final String SEND_SESSION="Controller?command=go_to_authorization_page";
    private static final String ID_EMPTY_RED ="Controller?command=go_to_admin_page&message= ne vybrano ne odnogo novostya";
    private static final String SEND_RED_TRY = "Controller?command=go_to_admin_page&message=novost uspeshno pereveden v arxiv";
    private static final String SEND_RED_CATCH = "Controller?command=go_to_admin_page&message=proizoshla oshibka pri udalenie novostey";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(SEND_SESSION);
			return;
		}
		User user = (User)session.getAttribute("user");
		if(user == null) {
			response.sendRedirect(SEND_SESSION);
			return;
		}
		if(! "admin".equals(user.getRole())) {
			session.removeAttribute("user");
			response.sendRedirect(SEND_SESSION);
			return;
		}
		
		if(null == request.getParameterValues("id_news")) {
			response.sendRedirect(ID_EMPTY_RED);
			return;
		}
		String [] idNewsS = request.getParameterValues("id_news");
		int s = idNewsS.length;
		int [] idNews = new int[s];
		
		for(int i = 0; i < s; i++) {
			idNews[i] = Integer.parseInt(idNewsS[i]);
		}
			
		try {
			NEWSSERVICE.delete(idNews);
			response.sendRedirect(SEND_RED_TRY);
		} catch (ServiceException e) {
		    response.sendRedirect(SEND_RED_CATCH);
		}
		
	}

}
