package by.itacademy.newsportal.dao.connectionpool;

public class ConnectionPoolException extends Exception{

	private static final long serialVersionUID = 1L;
    
	public ConnectionPoolException() {
		super();
	}
	
	public ConnectionPoolException(String message) {
		super(message);
	}
	
	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}
}
