package by.itacademy.newsportal.service.impl;

import java.util.List;

import by.itacademy.newsportal.bean.News;
import by.itacademy.newsportal.dao.DAOException;
import by.itacademy.newsportal.dao.DAOProvider;
import by.itacademy.newsportal.dao.NewsDAO;
import by.itacademy.newsportal.service.NewsService;
import by.itacademy.newsportal.service.ServiceException;

public class NewServiceImpl implements NewsService {
    
	private static final DAOProvider provider = DAOProvider.getInstance();
	private static final NewsDAO NEWSDAO = provider.getNewsDAO();
	public static final String PATTERN_TITLE = "^[a-zA-Z][a-zA-Z0-9-_\\.]{6,30}$";
	public static final String PATTERN_BRIEF = "^[a-zA-Z][a-zA-Z0-9-_\\.]{50,120}$";
	public static final String PATTERN_CONTENT = "^[a-zA-Z][a-zA-Z0-9-_\\.]{500,}$";
	@Override
	public void add(News news) throws ServiceException{
		
		try {
			NEWSDAO.add(news);
		}catch(DAOException e) {
			throw new ServiceException("Oshibka pri dobavleniye novostya", e);
		}
		
	}

	@Override
	public void update(News news) throws ServiceException{
		try {
			NEWSDAO.update(news);
		}catch(DAOException e) {
			throw new ServiceException("Oshibka pri obnovleniye novostya", e);
		}
	}

	@Override
	public void delete(News news) throws ServiceException {
		try {
			NEWSDAO.delete(news);
		}catch(DAOException e) {
			throw new ServiceException("Oshibka pri udaleniyie novostya", e);
		}
	}

	@Override
	public List<News> getLastNews() throws ServiceException {
		try {
			return NEWSDAO.getLastNews();
		}catch(DAOException e) {
			throw new ServiceException("Oshibka pri polucheniye news", e);
		}
		
		
	}

	@Override
	public List<News> getCategNews(String category) throws ServiceException {
		
		try {
			return NEWSDAO.getCategNews(category);
		} catch (DAOException e) {
			throw new ServiceException("Oshibka pri polucheniye category news", e);
		}
		
	}

	@Override
	public News getOneNews(int id) throws ServiceException {

		try {
			return NEWSDAO.getOneNews(id);
		} catch (DAOException e) {
			throw new ServiceException("Oshibka pri polucheniye odnogo news", e);
		}
	}

	

}
