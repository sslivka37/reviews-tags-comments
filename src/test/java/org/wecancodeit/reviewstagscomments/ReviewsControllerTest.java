package org.wecancodeit.reviewstagscomments;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;


import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.Optional;

public class ReviewsControllerTest {
	
	@InjectMocks
	private ReviewController underTest;
	
	@Mock
	private Review review;
	
	@Mock
	private Review anotherReview;
	
	@Mock
	private Tag tag;
	
	@Mock
	private Tag anotherTag;
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private TagRepository tagRepo;
	
	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void should_Add_Single_Review_To_Model() throws ReviewNotFoundException {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		
		underTest.findOneReview(arbitraryReviewId, model);
		verify(model).addAttribute("reviews", review);
		
	}

	
	@Test
	public void should_Add_All_Reviews_To_Model() {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		
		underTest.findAllReviews(model);		
		verify(model).addAttribute("reviews", allReviews);
				
	}
	
	@Test
	public void should_Add_Single_Tag_To_Model() throws TagNotFoundException {
		long arbitraryTagId = 1;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		
		underTest.findOneTag(arbitraryTagId, model);
		verify(model).addAttribute("tags", tag);
	}
		
	
	
	@Test
	public void should_Add_All_Tags_To_Model() {
		Collection<Tag> allTags = Arrays.asList(tag, anotherTag);
		when(tagRepo.findAll()).thenReturn(allTags);
		
		
		underTest.findAllTags(model);		
		verify(model).addAttribute("tags", allTags);
	}
	
	

}
