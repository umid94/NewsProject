package by.itacademy.newsportal.controller.impl;

import java.io.IOException;

import by.itacademy.newsportal.bean.RegistrationInfo;
import by.itacademy.newsportal.controller.Command;
import by.itacademy.newsportal.service.ServiceException;
import by.itacademy.newsportal.service.ServiceProvider;
import by.itacademy.newsportal.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationNewUser implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final UserService userService = provider.getUserService();

	private static final String SENDRED_TRY = "Controller?command=go_to_authorization_page&message=taram taram pam pam pozdravlyayu avtorizuysya";
	private static final String SENDRED_CATCH = "Controller?command=go_to_registration_page&message=pojaluysta poprobuyte eshe raz ";
	private static final String SENDRED_DUPLICATE_CATCH = "Controller?command=go_to_registration_page&message=Uje sushestvuet takoy polzovatel, poprobuyte pomenyat login ili email";
	public static final String NAME = "firstname";
	public static final String SURNAME = "lastname";
	private static final String EMAIL = "email";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getParameter(NAME);
		String surname = (String) request.getParameter(SURNAME);
		String email = (String) request.getParameter(EMAIL);
		String login = (String) request.getParameter(LOGIN);
		String password = (String) request.getParameter(PASSWORD);

		RegistrationInfo info = new RegistrationInfo();
		info.setFirstName(name);
		info.setLastName(surname);
		info.setEmail(email);
		info.setLogin(login);
		info.setPassword(password);

		try {
			if (userService.validateDuplicate(login, email)) {
				response.sendRedirect(SENDRED_DUPLICATE_CATCH);
				return;
			}
			userService.registration(info);
			response.sendRedirect(SENDRED_TRY);

		} catch (ServiceException e) {
			response.sendRedirect(SENDRED_CATCH);
		}

	}

}
