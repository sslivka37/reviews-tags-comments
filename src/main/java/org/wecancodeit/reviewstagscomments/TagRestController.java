package org.wecancodeit.reviewstagscomments;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
public class TagRestController {
	
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@RequestMapping(" ")
	public Iterable <Tag> findAllTags(){
		return tagRepo.findAll();
	}
	
	
	@RequestMapping("/{id}")	
	public Optional<Tag> findOneTag(@PathVariable long id){
		return tagRepo.findById(id);
			
	}
	
	@RequestMapping("/{tagName}/reviews")
	public Collection<Review> findAllCReviewsByTags(@PathVariable(value="tagName") String tagName){
		Tag tag = tagRepo.findByNameIgnoreCaseLike(tagName);
		return reviewRepo.findByTagsContains(tag);
	}

}
