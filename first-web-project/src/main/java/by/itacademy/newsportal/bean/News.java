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
	private int user_id;
	
	public News() {
		super();
	}
	
	public News(int id, String title, String brief, String content, Date newsDate, int user_id) {
		super();
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.newsDate = newsDate;
		this.user_id = user_id;
	}
	public News(String title, String brief, String content,int user_id) {
	  super();
	  this.title = title;
	  this.brief = brief;
	  this.content = content;
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
		   result = prime * result + id;
		   result = prime * result + ((title == null) ? 0 : title.hashCode());  
		   result = prime * result + ((brief == null) ? 0 : brief.hashCode()); 
		   result = prime * result + ((content == null) ? 0 : content.hashCode()); 
		   result = prime * result + ((newsDate == null) ? 0 : newsDate.hashCode()); 
		   return result;	
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
	        return true;
	    }
	    if (obj == null || obj.getClass() != this.getClass()) {
	        return false;
	    }

	    News guest = (News) obj;
	        return id == guest.id
	        && (title == guest.title || (title != null &&title.equals(guest.getTitle()))) 
	        && (brief == guest.brief || (brief != null && brief .equals(guest.getBrief())))
	    	&& (content == guest.content || (content != null && content.equals(guest.getContent())))
	    	&& (newsDate == guest.newsDate || (newsDate != null && newsDate .equals(guest.getNewsDate())));
	    
	}
	
	

	@Override
	public String toString() {
		return getClass().getName() + "@" + "id=" + id + ", title=" + title + ", brief=" + brief + ", content=" + content + ", newsDate="
				+ newsDate;
	}
	
}
