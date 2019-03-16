package org.wecancodeit.reviewstagscomments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static java.lang.String.format;


@Entity
public class Review {


	@Id
	@GeneratedValue
	private long id;	
	
	
	@Lob
	private String content;
	
	private String name;
	private String image;
	
	
	@JsonIgnore
	@ManyToMany
	private Collection<Tag> tags;
	
	@JsonIgnore
	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;
	
	public Collection<String> getTagsUrls(){
		Collection<String>urls = new ArrayList<>();
		for(Tag t: tags) {
			urls.add(format("/reviews/%d/tags/%s", this.getId(), t.getName().toLowerCase()));
		}
		
		return urls;
	}
	
	//default constructor
	public Review() {
		
	}

	public Review(String name, String content, String image, Tag...tags) {
		this.name = name;
		this.content = content;
		this.image = image;
		this.tags = new HashSet<>(Arrays.asList(tags));
		
		
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getUrl() {
		return image;
	}
	
	public Collection<Tag> getTags() {
		return tags;
	}
	
	public Collection<Comment> getComments() {		
		return comments;
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
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

	

	
}