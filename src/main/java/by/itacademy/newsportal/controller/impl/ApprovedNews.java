package by.itacademy.newsportal.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final String ID_EMPTY_RED ="Controller?command=go_to_admin_page&message= ne vybrano ne odnogo novostya";
    private static final String SEND_RED_TRY = "Controller?command=get_disapproved_news&message=operatsiya vipolneno uspeshno";
    private static final String SEND_RED_CATCH ="Controller?command=get_disapproved_news&message=proizoshla oshibka poprobuyte eshe raz";
	
	private final static Logger log = LogManager.getLogger("mylogger");
    
    @Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			NEWSSERVICE.approvedNews(idNews);
			response.sendRedirect(SEND_RED_TRY);
			
		} catch (ServiceException e) {
			log.error("error when approving news", e);
			response.sendRedirect(SEND_RED_CATCH);
		}
		
	}

}
