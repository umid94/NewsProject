package by.itacademy.newsportal.dao.connectionpool;

import java.util.Locale;
import java.util.ResourceBundle;

public class DBNewsResourceManager {
	private final static String DB = "db_news";
	private final static DBNewsResourceManager instance = new DBNewsResourceManager();
	private ResourceBundle resourceBundle = ResourceBundle.getBundle(DB);

	public static DBNewsResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return resourceBundle.getString(key);
	}
}
