package org.wecancodeit.reviewstagscomments;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ReviewRestController {
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@RequestMapping("")
	public Iterable<Review> findAllReviews(){
		return reviewRepo.findAll();
	}
	
	@RequestMapping("/{id}")
	public Optional<Review> findOneReview(@PathVariable long id){
		return reviewRepo.findById(id);
	}
	
	@RequestMapping("/tags/{tagName}")
	public Collection<Review> findAllReviewsByTag(@PathVariable(value="tagName") String tagName){
		Tag tag = tagRepo.findByNameIgnoreCaseLike(tagName);
		return reviewRepo.findByTagsContains(tag);
	}

}
