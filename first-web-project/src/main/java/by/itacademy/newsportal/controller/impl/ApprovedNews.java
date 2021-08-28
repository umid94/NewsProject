package by.itacademy.newsportal.controller.impl;

import java.io.IOException;

import by.itacademy.newsportal.controller.Command;
import by.itacademy.newsportal.service.NewsService;
import by.itacademy.newsportal.service.ServiceException;
import by.itacademy.newsportal.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApprovedNews implements Command{
	private static final ServiceProvider provider = ServiceProvider.getInstance();
    private static final NewsService NEWSSERVICE = provider.getNewService();
    
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String [] idNewsS = request.getParameterValues("id_news");
		int s = idNewsS.length;
		 int [] idNews = new int[s];
		for(int i = 0; i < s; i++) {
			idNews[i] = Integer.parseInt(idNewsS[i]);
		}
			
		try {
			NEWSSERVICE.approvedNews(idNews);
			response.sendRedirect("Controller?command=get_disapproved_news&message=operatsiya vipolneno uspeshno");
			
		} catch (ServiceException e) {
			response.sendRedirect("Controller?command=get_disapproved_news&message=proizoshla oshibka poprobuyte eshe raz");
		}
		
	}

}
