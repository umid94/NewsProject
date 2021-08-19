package by.itacademy.newsportal.service.impl;

import by.itacademy.newsportal.bean.RegistrationInfo;
import by.itacademy.newsportal.bean.User;
import by.itacademy.newsportal.dao.DAOException;
import by.itacademy.newsportal.dao.DAOProvider;
import by.itacademy.newsportal.dao.UserDAO;
import by.itacademy.newsportal.service.ServiceException;
import by.itacademy.newsportal.service.UserService;

public class UserServiceImpl implements UserService {
	private static final DAOProvider provider = DAOProvider.getInstance();
	private static final UserDAO userDAO = provider.getUserDAO();
	
	
	public static final String PATTERN_FIRSTNAME = "^[a-zA-Z][a-zA-Z]{2,20}$";
	public static final String PATTERN_LASTNAME = "^[a-zA-Z][a-zA-Z]{2,20}$";
	public static final String PATTERN_LOGIN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{5,20}$";
	public static final String PATTERN_EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
	public static final String PATTERN_PASSWORD = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}";
	
	@Override
	public void registration(RegistrationInfo info) throws ServiceException {
		
		String firstname = (String)info.getFirstName();
		String lastname = (String)info.getLastName();
		String login = (String)info.getLogin();
		String email = (String)info.getEmail();
		String password = (String)info.getPassword();
		
		if (!firstname.matches(PATTERN_FIRSTNAME)) {
			throw new ServiceException("Invalid name. Try again");
		}
		if (!lastname.matches(PATTERN_LASTNAME)) {
			throw new ServiceException("Invalid lastname. Try again");
		}
		if (!login.matches(PATTERN_LOGIN)) {
			throw new ServiceException("Invalid login. Try again");
		}
		if (!email.matches(PATTERN_EMAIL)) {
			throw new ServiceException("Invalid email. Try again");
		}
		if (!password.matches(PATTERN_PASSWORD)) {
			throw new ServiceException("Invalid password. Try again");
		}
		
	      try {
	 		userDAO.saveUser(info);
	      }catch(DAOException e) {
	    	  
	    	  throw new ServiceException("Error registration new User invalid data", e);
	      }
	
    }
	@Override
	public User authorizationUser(String login, String password) throws ServiceException {
		
		if (!login.matches(PATTERN_LOGIN)) {
			throw new ServiceException("Invalid login. Try again");
		}
		if (!password.matches(PATTERN_PASSWORD)) {
			throw new ServiceException("Invalid password. Try again");
		}
		
		try {
			return userDAO.getUser(login, password);
		} catch (DAOException e) {
			throw new ServiceException();
		}
		
	}
	@Override
	public void validateDuplicate(String login, String email) throws ServiceException {
		
		if (!login.matches(PATTERN_LOGIN)) {
			throw new ServiceException("Invalid login. Try again");
		}
		if (!email.matches(PATTERN_EMAIL)) {
			throw new ServiceException("Invalid email. Try again");
		}
		
		try {
			
			userDAO.validateDuplicate(login, email);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		
	}	
}
