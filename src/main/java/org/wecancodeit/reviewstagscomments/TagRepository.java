package org.wecancodeit.reviewstagscomments;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Tag findByName(String tagName);

	Tag findByNameIgnoreCaseLike(String tagName);

	Collection<Tag> findByReviewsContains(Review review);

	



}
