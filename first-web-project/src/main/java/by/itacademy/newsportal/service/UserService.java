package by.itacademy.newsportal.service;

import by.itacademy.newsportal.bean.RegistrationInfo;
import by.itacademy.newsportal.bean.User;

public interface UserService {
   
	
	public void registration (RegistrationInfo info) throws ServiceException;
	
	public User authorizationUser(String login, String password)throws ServiceException;
	
	public boolean validateDuplicate(String login, String email)throws ServiceException;
}
