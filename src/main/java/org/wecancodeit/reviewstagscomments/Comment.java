package org.wecancodeit.reviewstagscomments;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {
	
	
	@Id
	@GeneratedValue
	private long id;

	private String title;
	private String content;
	
	@JsonIgnore
	@ManyToOne
	private Review review;
	
	
	
	public Comment () {
		
	}

	public Comment(String title, String content, Review review) {
		this.title = title;
		this.content = content;
		this.review = review;
		
	}
	
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	

}
