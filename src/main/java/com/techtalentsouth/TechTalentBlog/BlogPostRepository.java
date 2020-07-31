package com.techtalentsouth.TechTalentBlog;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.techtalentsouth.TechTalentBlog.BlogPost.BlogPost;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {
	List<BlogPost> findAll();
	List<BlogPost> findByAuthorIgnoreCase(String author);
//	List<BlogPost> save(String blogPost);
	
	BlogPost findById(long id);
	BlogPost deleteById(long id);
	

}


