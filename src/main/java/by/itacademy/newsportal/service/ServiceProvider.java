package by.itacademy.newsportal.service;

import by.itacademy.newsportal.service.impl.NewServiceImpl;
import by.itacademy.newsportal.service.impl.UserServiceImpl;

public final class ServiceProvider {
  
	private static final ServiceProvider instance = new ServiceProvider();
	
	private final UserService userService = new UserServiceImpl();
	private final NewsService newService = new NewServiceImpl();
	
	private ServiceProvider() {}
	
	public static ServiceProvider getInstance() {
		return instance;
	}
	
	public UserService getUserService() {
		return userService;
	}
	
	public NewsService getNewService() {
		return newService;
	}
}
