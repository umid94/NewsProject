package by.itacademy.newsportal.bean;

import java.io.Serializable;
import java.sql.Date;

public class News implements Serializable{
   
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String brief;
	private String content;
	private Date newsDate;
	private String category;
	private String status;
	private int user_id;
	
	public News() {
		super();
	}
	
	public News(int id, String title, String brief, String content, Date newsDate, String category, String status, int user_id) {
		super();
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.newsDate = newsDate;
		this.status = status;
		this.category = category;
		this.user_id = user_id;
	}
	public News(String title, String brief, String content, String category, int user_id) {
	  super();
	  this.title = title;
	  this.brief = brief;
	  this.content = content;
	  this.category = category;
	  this.user_id = user_id;
	  }
    
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		result = prime * result + ((newsDate == null) ? 0 : newsDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + user_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (newsDate == null) {
			if (other.newsDate != null)
				return false;
		} else if (!newsDate.equals(other.newsDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return getClass().getName() + "@" + "id=" + id + ", title=" + title + ", brief=" + brief + ", content=" + content + ", newsDate="
				+ newsDate + ", category=" + category + ", status=" + status + ", user_id=" + user_id;
	}
	
}
