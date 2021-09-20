package by.itacademy.newsportal.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Filter7 implements Filter {


	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession(true);
        String guest = "guest";
        String example = (String) session.getAttribute("guest");
		if(guest.equals(example)) {
			RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("/WEB-INF/jsp/authorization.jsp");
			requestDispatcher.forward(httpRequest, httpResponse);
			session.removeAttribute("guest");
			return;
		}
		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	
}
