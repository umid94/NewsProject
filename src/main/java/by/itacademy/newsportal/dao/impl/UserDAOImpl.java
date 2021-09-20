package by.itacademy.newsportal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.itacademy.newsportal.bean.RegistrationInfo;
import by.itacademy.newsportal.bean.User;
import by.itacademy.newsportal.dao.DAOException;
import by.itacademy.newsportal.dao.UserDAO;
import by.itacademy.newsportal.dao.connectionpool.ConnectionPool;
import by.itacademy.newsportal.dao.connectionpool.ConnectionPoolException;

public class UserDAOImpl implements UserDAO {

	private static final ConnectionPool CONNEC_POOL = ConnectionPool.getInstance();
	private static final String REGISTRATION_USER = "INSERT INTO users(name,surname,email,login,role,password) VALUES(?,?,?,?,?,?)";
    private static final String AUTHORIZATION_USER = "SELECT * FROM users WHERE login=? AND password=?";
    private static final String VALID_LOGIN_DUPLIC = "SELECT * FROM users WHERE login=? AND email=?";
	@Override
	public void saveUser(RegistrationInfo info) throws DAOException {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement(REGISTRATION_USER);

			ps.setString(1, info.getFirstName());
			ps.setString(2, info.getLastName());
			ps.setString(3, info.getEmail());
			ps.setString(4, info.getLogin());
			ps.setString(5, info.getRole());
			ps.setString(6, info.getPassword());
			ps.executeUpdate();
     
		} catch (SQLException e) {
			throw new DAOException("Error occurred while registering a user", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error connect to ConnectionPool", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}

	}

	@Override
	public User getUser(String login, String password) throws DAOException {

		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement(AUTHORIZATION_USER);
			ps.setString(1, login);
			ps.setString(2, password);

		    rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("idusers");
				String log = rs.getString("login");
				String role = rs.getString("role");
				user = new User(id, log, role);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Error occurred while registering a user", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error connect to ConnectionPool", e);
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
				throw new DAOException(e);
			}
		}
		if (user == null) {
			throw new DAOException("parol ili login ne pravilniy ");
		}
		return user;

	}

	@Override
	public boolean validateDuplicate(String login, String email) throws DAOException {
        
		boolean isDuplicate = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = CONNEC_POOL.takeConnection();
			ps = conn.prepareStatement( VALID_LOGIN_DUPLIC);
			ps.setString(1, login);
			ps.setString(2, email);
		    rs = ps.executeQuery();
		    String log = null;
		    String ema = null;
		    while(rs.next()) {
		    	log= rs.getString("login");
		    	ema = rs.getString("email");
		    }
			if(log != null || ema != null) {
				isDuplicate = true;
			}
		} catch (SQLException e) {
			System.out.print("lalalala");
			return true;
		} catch (ConnectionPoolException e) {
			throw new DAOException("Error connect to ConnectionPool", e);
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
		return isDuplicate;
		
	}
}
