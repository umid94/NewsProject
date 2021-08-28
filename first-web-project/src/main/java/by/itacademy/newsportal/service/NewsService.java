package by.itacademy.newsportal.service;

import java.util.List;

import by.itacademy.newsportal.bean.News;
import by.itacademy.newsportal.dao.DAOException;

public interface NewsService {

	public void add(News news)throws ServiceException;

	public void update(News news)throws ServiceException;
	
	public void delete(int mass[])throws ServiceException;
	
	public List<News> getLastNews()throws ServiceException; 
	
	public List<News> getCategNews(String category)throws ServiceException;
	
	public News getOneNews(int id)throws ServiceException;
	
    public List<News> getDisApprovedNews()throws ServiceException;
	
	public List<News> getUserNews(int idUser)throws ServiceException;
	
	public int getCountNews()throws ServiceException;
	
	public List<News> getAllPagNews(int page)throws ServiceException;
	
	public boolean approvedNews(int mass[])throws ServiceException;
	
	public List<News> getDeletedNews()throws ServiceException;
}