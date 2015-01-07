package springboot.data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String title;
	
	@Column
	private String content;
	
	@Column
	private Date created;
	
	@Column
	private Date changed;

	/**
	 * Application specific constructor
	 * @param title
	 * @param content
	 */
	public Post(String title, String content) {
		this.title = title;
		this.content = content;
		this.created = new Date();
		this.changed = this.created;
	}
	
	/**
	 * Entity should always have an empty constructor.
	 * Spring Data builds entity objects with empty constructor
	 * and fills in all the properties afterwards
	 */
	public Post() {}

	/**
	 * IDE generated toString implementation
	 * Good for logging
	 */
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content
				+ ", created=" + created + ", changed=" + changed + "]";
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
		this.changed = new Date();
	}
	
	/*
	 * Setters are also required by a template engine to successfully set values on objects from templates
	 */
	public void setContent(String content) {
		this.content = content;
		this.changed = new Date();
	}

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
}
