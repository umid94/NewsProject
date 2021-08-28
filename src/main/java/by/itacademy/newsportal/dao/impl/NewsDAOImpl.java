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
    private static final String INSERT_NEWS = "INSERT INTO news(title,brief,content,category,status,date,id_user) VALUES(?,?,?,?,?,?,?)";
    private static final String GET_LAST_NEWS = "SELECT * FROM news WHERE status='approved' ORDER BY id DESC LIMIT 5";
    private static final String UPDATE_NEWS = "UPDATE news SET title=?,brief=?,content=?,category=?,date=? WHERE id=?";
    private static final String DELETE_NEWS = "UPDATE news SET status='archive' WHERE id=?";
    private static final String GET_LAST_CATEG_NEWS = "SELECT * FROM news WHERE category=? and status='approved'";
    private static final String GET_ONE_NEWS = "SELECT * FROM news WHERE id=?";
    private static final String GET_DISAPPROVED_NEWS = "SELECT * FROM news WHERE status='disapproved'";
    private static final String GET_USER_NEWS = "SELECT * FROM news WHERE status='approved' and id_user=?";
    private static final String GET_COUNT_NEWS = "SELECT count(*) FROM news";
    private static final String PAGINATION_NEWS = "SELECT * FROM news WHERE status='approved' ORDER BY id DESC LIMIT ?,5";
    private static final String APPROVED_NEWS = "UPDATE news SET status='approved' WHERE id=?";
    private static final String GET_DELETED_NEWS = "SELECT * FROM news WHERE status='archive'";
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
			ps.setString(4, news.getCategory());
			if(news.getStatus() != null){
				ps.setString(5, news.getStatus());
			}else {
				ps.setString(5, "disapproved");
			}
			ps.setDate(6, Date.valueOf(LocalDate.now()));
			ps.setInt(7, news.getUser_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Error occurred while add new News", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error while connecting to ConnectionPool", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
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
			ps.setString(4, news.getCategory());
			ps.setDate(5, Date.valueOf(LocalDate.now()));
			ps.setInt(6, news.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("oshibka pri obnovleniye NOvostya ", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException();
		} finally {
			try {
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
				newses.add(new News(rs.getString("title"),rs.getString("brief"), rs.getString("content"),rs.getString("category"), rs.getInt("id_user")));
			}
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
	public void delete(int[] mass) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(DELETE_NEWS);
			for(int p : mass) {
		        ps.setInt(1, p);
		        ps.addBatch();
		    }
			int[] affectedRecords = ps.executeBatch();
			System.out.print(affectedRecords);
		    
		    conn.commit();
		}catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DAOException("Oshibka pri odobrenie News", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException();
		} finally {
			try {
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
				newses.add(new News(rs.getInt("id"),rs.getString("title"),rs.getString("brief"), rs.getString("content"), rs.getDate("date"), rs.getString("category"), rs.getString("status"), rs.getInt("id_user")));			
				}
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
				news = new News(rs.getInt("id"),rs.getString("title"),rs.getString("brief"), rs.getString("content"), rs.getDate("date"), rs.getString("category"), rs.getString("status"), rs.getInt("id_user"));		
				}
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

	@Override
	public List<News> getDisApprovedNews() throws DAOException {

		List<News> newses = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement(GET_DISAPPROVED_NEWS);
			
		    rs = ps.executeQuery();
			while (rs.next()) {
				newses.add(new News(rs.getInt("id"),rs.getString("title"),rs.getString("brief"), rs.getString("content"), rs.getDate("date"), rs.getString("category"), rs.getString("status"), rs.getInt("id_user")));		
				}
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
	public List<News> getUserNews(int idUser) throws DAOException {

		List<News> newses = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement(GET_USER_NEWS);
			ps.setInt(1, idUser);
		    rs = ps.executeQuery();
			while (rs.next()) {
				newses.add(new News(rs.getInt("id"),rs.getString("title"),rs.getString("brief"), rs.getString("content"), rs.getDate("date"), rs.getString("category"), rs.getString("status"), rs.getInt("id_user")));
				}
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
	public int getCountNews() throws DAOException {
		
		int countNews = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement(GET_COUNT_NEWS);
		    rs = ps.executeQuery();
		    if (rs.next()) { 
		        countNews = rs.getInt(1);
		    }
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
	 return countNews;
	}

	@Override
	public List<News> getAllPagNews(int page) throws DAOException {
		
		List<News> newses = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement(PAGINATION_NEWS);
		    ps.setInt(1, page);
		    rs = ps.executeQuery();
			while (rs.next()) {
				newses.add(new News(rs.getInt("id"),rs.getString("title"),rs.getString("brief"), rs.getString("content"), rs.getDate("date"), rs.getString("category"), rs.getString("status"), rs.getInt("id_user")));
				}
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
	public boolean approvedNews(int[] mass) throws DAOException {
		
		boolean isOkay = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(APPROVED_NEWS);
			for(int p : mass) {
		        ps.setInt(1, p);
		        ps.addBatch();
		    }
			int[] affectedRecords = ps.executeBatch();
			System.out.print(affectedRecords);
		    isOkay = true;
		    
		    conn.commit();
		}catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DAOException("Oshibka pri odobrenie News", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException();
		} finally {
			try {
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

		
		
		return isOkay;
	}

	@Override
	public List<News> getDeletedNews() throws DAOException {
		
		List<News> newses = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement(GET_DELETED_NEWS);
		    rs = ps.executeQuery();
		    System.out.print("hahaahahh");
			while (rs.next()) {
				newses.add(new News(rs.getInt("id"),rs.getString("title"),rs.getString("brief"), rs.getString("content"), rs.getDate("date"), rs.getString("category"), rs.getString("status"), rs.getInt("id_user")));		
				}
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


}
