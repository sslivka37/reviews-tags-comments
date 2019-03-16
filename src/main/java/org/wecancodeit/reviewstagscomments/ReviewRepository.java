package org.wecancodeit.reviewstagscomments;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
	Collection<Review> findByTagsId(long tagId);

	Collection<Review> findByTagsContains(Tag tag);

}


