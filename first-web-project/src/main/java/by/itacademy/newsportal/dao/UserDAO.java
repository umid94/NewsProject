package by.itacademy.newsportal.dao;

import by.itacademy.newsportal.bean.RegistrationInfo;
import by.itacademy.newsportal.bean.User;
import by.itacademy.newsportal.service.ServiceException;
public interface UserDAO {
	
	public void saveUser (RegistrationInfo info) throws DAOException;
	
	public User getUser(String password, String login) throws DAOException ;
	
	public void validateDuplicate(String login, String email)throws DAOException;
}

