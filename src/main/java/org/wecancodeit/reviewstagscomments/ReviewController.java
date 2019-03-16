package org.wecancodeit.reviewstagscomments;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ReviewController {
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private TagRepository tagRepo;

	
	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value="id")long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);
	
		if(review.isPresent()) {
			model.addAttribute("reviews", review.get());
			return "review";
			
		}
		
		throw new ReviewNotFoundException();
		
		}

	@RequestMapping("/show-reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return("reviews");
		
	}

	@RequestMapping("/tag")
	public String findOneTag(@RequestParam(value="id")long id, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(id);
		
		if(tag.isPresent()) {
			model.addAttribute("tags", tag.get());
			model.addAttribute("reviews", reviewRepo.findByTagsContains(tag.get()));
			return "tag";
		}
			
		throw new TagNotFoundException();
	}

	@RequestMapping("/show-tags")
	public String findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return ("tags");
		
	}
	
	@RequestMapping("/find-by-tag")
	public String findReviewsByTag(String tagName, Model model) {
		Tag tag = tagRepo.findByNameIgnoreCaseLike(tagName);
		model.addAttribute("reviews", reviewRepo.findByTagsContains(tag));
		
		return "/tag";
	}
	

}
