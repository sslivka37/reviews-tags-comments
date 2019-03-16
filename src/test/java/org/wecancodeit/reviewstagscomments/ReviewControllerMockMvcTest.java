package org.wecancodeit.reviewstagscomments;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@MockBean
	private TagRepository tagRepo;
	
	@Mock
	private Review review;
	
	@Mock
	private Review anotherReview;
	
	@Mock
	private Tag tag;
	
	@Mock
	private Tag anotherTag;
	
	@Test
	public void should_Route_To_Single_Review_View() throws Exception {
		long arbitraryReviewId =1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
		
	}
	
	@Test
	public void should_Be_Ok_For_Single_Review() throws Exception {
		long arbitraryReviewId =1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());	
		
	}
	
	@Test
	public void should_Not_Be_Ok_For_Single_Review() throws Exception {
		mvc.perform(get("/review?id=1")).andExpect(status().isNotFound());	
		
	}
	
	
	@Test
	public void should_Put_Single_Review_Into_Model() throws Exception {
		when(reviewRepo.findById(1L)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(model().attribute("reviews", is(review)));
	}
	
	
	@Test
	public void should_Route_To_All_Reviews_View() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(view().name(is("reviews")));
	}
	
	
	@Test
	public void should_Be_Ok_For_All_Reviews() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(status().isOk());
	}
	
	
	@Test
	public void should_Put_All_Reviews_Into_Model() throws Exception {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews", is (allReviews)));
	}
	
	
	@Test
	public void should_Route_To_Single_Tag_View() throws Exception {
		long arbitraryTagId =42;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=42")).andExpect(view().name(is("tag")));
		
	}
	
	@Test
	public void should_Be_Ok_For_Single_Tag() throws Exception {
		long arbitraryTagId =42;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=42")).andExpect(status().isOk());	
	}
	
	@Test
	public void should_Not_Be_Ok_For_Single_Tag() throws Exception {
		mvc.perform(get("/tag?id=42")).andExpect(status().isNotFound());	
		
	}
	
	@Test
	public void should_Put_Single_Tag_Into_Model() throws Exception {
		when(tagRepo.findById(42L)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=42")).andExpect(model().attribute("tags", is(tag)));
	}
	
	@Test
	public void should_Be_Ok_For_All_Tags() throws Exception {
		mvc.perform(get("/show-tags")).andExpect(status().isOk());
	}
	
	@Test
	public void should_Put_All_Tags_Into_Model() throws Exception {
		Collection<Tag> allTags = Arrays.asList(tag, anotherTag);
		when(tagRepo.findAll()).thenReturn(allTags);
		
		mvc.perform(get("/show-tags")).andExpect(model().attribute("tags", is (allTags)));
	}
	

}
