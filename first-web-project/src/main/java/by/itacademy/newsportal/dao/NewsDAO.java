package by.itacademy.newsportal.dao;

import java.util.List;

import by.itacademy.newsportal.bean.News;
import by.itacademy.newsportal.service.ServiceException;

public interface NewsDAO {
	
	public void add(News news) throws DAOException;
	
	public void update(News news)throws DAOException;
	
	public List<News> getLastNews()throws DAOException;
	
	public void delete(News news)throws DAOException;
	
	public List<News> getCategNews(String category)throws DAOException;
	
	public News getOneNews(int id)throws DAOException;
}
