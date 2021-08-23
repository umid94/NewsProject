package by.itacademy.newsportal.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.itacademy.newsportal.bean.News;
import by.itacademy.newsportal.dao.DAOException;
import by.itacademy.newsportal.dao.NewsDAO;
import by.itacademy.newsportal.dao.connectionpool.ConnectionPool;
import by.itacademy.newsportal.dao.connectionpool.ConnectionPoolException;

public class NewsDAOImpl implements NewsDAO {
    
	private static final ConnectionPool CONNEC_POOL = ConnectionPool.getInstance();
    private static final String INSERT_NEWS = "INSERT INTO news(title,brief,content,date,id_user) VALUES(?,?,?,?,?)";
    private static final String GET_LAST_NEWS = "SELECT * FROM news ORDER BY id DESC LIMIT 5";
    private static final String UPDATE_NEWS = "UPDATE news SET title=?,brief=?,content=?,date=? WHERE id=?";
    private static final String DELETE_NEWS = "DELETE FROM news WHERE id=?";
    private static final String GET_LAST_CATEG_NEWS = "SELECT * FROM news WHERE category=?";
    private static final String GET_ONE_NEWS = "SELECT * FROM news WHERE id=?";
	@Override
	public void add(News news) throws DAOException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement(INSERT_NEWS);

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setDate(4, Date.valueOf(LocalDate.now()));
			ps.setInt(5, news.getUser_id());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Error occurred while add new News", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error while connecting to ConnectionPool", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				throw new DAOException("close error Connection DataBase", e);
			}
		}
	}

	@Override
	public void update(News news) throws DAOException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement(UPDATE_NEWS);

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBrief());
			ps.setString(3, news.getContent());
			ps.setDate(4, Date.valueOf(LocalDate.now()));
			ps.setInt(5, news.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("oshibka pri obnovleniye NOvostya ", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				throw new DAOException();
			}
		}

		
	}

	@Override
	public List<News> getLastNews() throws DAOException {
		
		List<News> newses = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement(GET_LAST_NEWS);
			
		    rs = ps.executeQuery();
			while (rs.next()) {
				newses.add(new News(rs.getInt("id"),rs.getString("title"),rs.getString("brief"), rs.getString("content"), rs.getDate("date"), rs.getInt("id_user")));
			}
			System.out.print("v baze");
		} catch (SQLException e) {
			throw new DAOException("Oshibka pri chtenie News", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Oshibka svyazannaya s Connection", e);
		}finally {
			try {
				if(rs != null) {
				   rs.close();
				} 
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new DAOException();
			}
		}
	 return newses;
	}

	@Override
	public void delete(News news) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement(DELETE_NEWS);
         
			ps.setInt(1, news.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("oshibka pri udaleniye Novostya  v SQL ", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				throw new DAOException();
			}
		}

		
	}

	@Override
	public List<News> getCategNews(String category) throws DAOException {
		
		List<News> newses = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement(GET_LAST_CATEG_NEWS);
			ps.setString(1, category);
		    rs = ps.executeQuery();
			while (rs.next()) {
				newses.add(new News(rs.getInt("id"),rs.getString("title"),rs.getString("brief"), rs.getString("content"), rs.getDate("date"), rs.getInt("id_user")));
			}
			System.out.print("v baze");
		} catch (SQLException e) {
			throw new DAOException("Oshibka pri chtenie News", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Oshibka svyazannaya s Connection", e);
		}finally {
			try {
				if(rs != null) {
				   rs.close();
				} 
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new DAOException();
			}
		}
	 return newses;
		
		
	}

	@Override
	public News getOneNews(int id) throws DAOException {
		News news= null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement(GET_ONE_NEWS);
			ps.setInt(1, id);
		    rs = ps.executeQuery();
			while (rs.next()) {
				news = new News(rs.getInt("id"),rs.getString("title"),rs.getString("brief"), rs.getString("content"), rs.getDate("date"), rs.getInt("id_user"));
			}
			System.out.print("v baze");
		} catch (SQLException e) {
			throw new DAOException("Oshibka pri chtenie News", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Oshibka svyazannaya s Connection", e);
		}finally {
			try {
				if(rs != null) {
				   rs.close();
				} 
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new DAOException();
			}
		}
	 return news;
		
	}


}
