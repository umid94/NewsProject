package by.itacademy.newsportal.dao;

import java.util.List;

import by.itacademy.newsportal.bean.News;
import by.itacademy.newsportal.service.ServiceException;

public interface NewsDAO {
	
	public void add(News news) throws DAOException;
	
	public void update(News news)throws DAOException;
	
	public List<News> getLastNews()throws DAOException;
	
	public void delete(int[] mass)throws DAOException;
	
	public List<News> getCategNews(String category)throws DAOException;
	
	public News getOneNews(int id)throws DAOException;
	
	public List<News> getDisApprovedNews()throws DAOException;
	
	public List<News> getUserNews(int idUser)throws DAOException;
	
	public int getCountNews()throws DAOException;
	
	public List<News> getAllPagNews(int page)throws DAOException;
	
	public boolean approvedNews(int mass[])throws DAOException;
	
	public List<News> getDeletedNews()throws DAOException;
	
}
