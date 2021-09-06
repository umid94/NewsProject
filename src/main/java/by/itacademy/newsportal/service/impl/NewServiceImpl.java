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
	public static final String PATTERN_TITLE = "^[a-zA-Z][a-zA-Z0-9-_\\.]{6,50}$";
	public static final String PATTERN_BRIEF = "^[a-zA-Z][a-zA-Z0-9-_\\.]{50,300}$";
	public static final String PATTERN_CONTENT = "^[a-zA-Z][a-zA-Z0-9-_\\.]{500,2000}$";
	@Override
	public void add(News news) throws ServiceException{
		
		String title = news.getTitle();
		String brief = news.getBrief();
		String content = news.getContent();
		
		if(!title.matches(PATTERN_TITLE)) {
			throw new ServiceException("Invalid title. Try again");
		}
		if(!brief.matches(PATTERN_BRIEF)) {
			throw new ServiceException("Invalid brief. Try again");
		}
		if(!content.matches(PATTERN_CONTENT)) {
			throw new ServiceException("Invalid content. Try again");
		}
		
		try {
			NEWSDAO.add(news);
		}catch(DAOException e) {
			throw new ServiceException("error when adding news", e);
		}	
	}

	@Override
	public void update(News news) throws ServiceException{
		
		String title = news.getTitle();
		String brief = news.getBrief();
		String content = news.getContent();
		
		if(title.matches(PATTERN_TITLE)) {
			throw new ServiceException("Invalid title. Try again");
		}
		if(brief.matches(PATTERN_BRIEF)) {
			throw new ServiceException("Invalid brief. Try again");
		}
		if(content.matches(PATTERN_CONTENT)) {
			throw new ServiceException("Invalid content. Try again");
		}
		
		try {
			NEWSDAO.update(news);
		}catch(DAOException e) {
			throw new ServiceException("error when updating news", e);
		}
	}

	@Override
	public void delete(int[] massIdNEws) throws ServiceException {
		try {
			NEWSDAO.delete(massIdNEws);
		}catch(DAOException e) {
			throw new ServiceException("error when deleting news", e);
		}
	}

	@Override
	public List<News> getLastNews() throws ServiceException {
		try {
			return NEWSDAO.getLastNews();
		}catch(DAOException e) {
			throw new ServiceException("error while reading the latest news", e);
		}
	}

	@Override
	public List<News> getCategNews(String category) throws ServiceException {
		try {
			return NEWSDAO.getCategNews(category);
		} catch (DAOException e) {
			throw new ServiceException("error when reading the latest news categories", e);
		}
	}

	@Override
	public News getOneNews(int id) throws ServiceException {

		try {
			return NEWSDAO.getOneNews(id);
		} catch (DAOException e) {
			throw new ServiceException("error while reading one news item", e);
		}
	}

	@Override
	public List<News> getDisApprovedNews() throws ServiceException {
		try {
			return NEWSDAO.getDisApprovedNews();
		} catch (DAOException e) {
			throw new ServiceException("error while reading unapproved news",e);
		}
		
	}

	@Override
	public List<News> getUserNews(int idUser) throws ServiceException {
		try {
			return NEWSDAO.getUserNews(idUser);
		} catch (DAOException e) {
			throw new ServiceException("error while reading deleted news",e);
		}
	}

	@Override
	public int getCountNews() throws ServiceException {
		try {
			return NEWSDAO.getCountNews();
		} catch (DAOException e) {
           throw new ServiceException("error when reading general news numbers",e);
		}
		
	}

	@Override
	public List<News> getAllPagNews(int page) throws ServiceException {
		
		try {
			return NEWSDAO.getAllPagNews(page);
		} catch (DAOException e) {
			throw new ServiceException("error while reading all news",e);	
		}
		
	}

	@Override
	public boolean approvedNews(int[] massIdNews) throws ServiceException {
		
		try {
			return NEWSDAO.approvedNews(massIdNews);
		} catch (DAOException e) {
			throw new ServiceException("news approval error", e);
		}
	}

	@Override
	public List<News> getDeletedNews() throws ServiceException {
		try {
			return NEWSDAO.getDeletedNews();
		} catch (DAOException e) {
			throw new ServiceException("error while reading deleted news", e);
		}
	}

}
