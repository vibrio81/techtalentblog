package com.tts.techtalentblog.repo;


import com.tts.techtalentblog.model.BlogPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {

    @Query(value = "SELECT * from BLOG_POST where TITLE LIKE ?1 OR AUTHOR LIKE ?1 OR BLOG_ENTRY LIKE ?1", nativeQuery = true)
     List<BlogPost> search(String keyword);

}
