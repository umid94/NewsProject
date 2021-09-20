package by.itacademy.newsportal.dao;

import by.itacademy.newsportal.dao.impl.NewsDAOImpl;
import by.itacademy.newsportal.dao.impl.UserDAOImpl;

public final class DAOProvider {
 
	private static final DAOProvider instance = new DAOProvider();
     
	private final NewsDAO newsDAO = new NewsDAOImpl();
	private final UserDAO userDAO = new UserDAOImpl();
    
	private DAOProvider() {}

	public static DAOProvider getInstance() {
		return instance;
	}

	public NewsDAO getNewsDAO() {
		return newsDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	
}
