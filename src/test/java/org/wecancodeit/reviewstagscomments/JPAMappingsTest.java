package org.wecancodeit.reviewstagscomments;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.containsInAnyOrder;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {
	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CommentRepository commentRepo;
	
	
	
	
	@Test
	public void should_Save_And_Load_Tag() {
		Tag tag = tagRepo.save(new Tag ("tag"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Tag>result = tagRepo.findById(tagId);
		tag = result.get();
		assertThat(tag.getName(), is("tag"));		
	}
	
	
	@Test
	public void should_Generate_Tag_Id() {
		Tag tag = tagRepo.save(new Tag ("tag"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(tagId, is(greaterThan(0L)));
		
	}
	
	
	@Test
	public void should_Save_And_Load_Review() {
		Tag tag1 = tagRepo.save(new Tag("tag"));
		Review review = new Review("review", "content", "img", tag1);
		reviewRepo.save(review);
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getName(), is("review"));
		
	}
	
	@Test
	public void should_Establish_Review_To_Tag_Relationship() {
		Tag mmo = tagRepo.save(new Tag("MMO"));
		Tag fps = tagRepo.save(new Tag("First Person Shooter"));
		
		Review theDivision = new Review("The Division", "description", "Image", mmo, fps);
		theDivision = reviewRepo.save(theDivision);
		long divisionId = theDivision.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(divisionId);
		theDivision = result.get();
		
		assertThat(theDivision.getTags(), containsInAnyOrder(mmo, fps));
			
		}
	
	@Test
	public void should_Find_Reviews_For_Categories() {
		Tag fps = tagRepo.save(new Tag("FPS"));
		Review halo = reviewRepo.save(new Review("Halo", "description", "image", fps));
		Review theDivision = reviewRepo.save(new Review("The Division", "description", "image", fps));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForCategory = reviewRepo.findByTagsContains(fps);
		
		assertThat(reviewsForCategory, containsInAnyOrder(halo, theDivision));
		
	}
	
	@Test
	public void should_Find_Reviews_For_Category_Id() {
		Tag fps = tagRepo.save(new Tag("FPS"));
		long tagId = fps.getId();
		Review halo = reviewRepo.save(new Review("Halo", "description", "image", fps));
		Review theDivision = reviewRepo.save(new Review("The Division", "description", "image", fps));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForCategory = reviewRepo.findByTagsId(tagId);
		
		assertThat(reviewsForCategory, containsInAnyOrder(halo, theDivision));		
		
	}
	
	@Test
	public void should_Save_Comment_To_Review_Relationship() {
		Review review = new Review("name", "content", "img");
		reviewRepo.save(review);
		long reviewId = review.getId();
		
		Comment comment = new Comment("title", "comment", review);
		commentRepo.save(comment);
		
		Comment comment2 = new Comment("title2", "comment2", review);
		commentRepo.save(comment2);
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getComments(), containsInAnyOrder(comment, comment2));
		
	}
	
	

	
	
	
	
	
	

}
