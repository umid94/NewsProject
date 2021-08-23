package by.itacademy.newsportal.service;

import java.util.List;

import by.itacademy.newsportal.bean.News;

public interface NewsService {

	public void add(News news)throws ServiceException;

	public void update(News news)throws ServiceException;
	
	public void delete(News news)throws ServiceException;
	
	public List<News> getLastNews()throws ServiceException; 
	
	public List<News> getCategNews(String category)throws ServiceException;
	
	public News getOneNews(int id)throws ServiceException;
		
}