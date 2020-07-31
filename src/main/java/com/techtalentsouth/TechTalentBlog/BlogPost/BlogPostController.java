package com.techtalentsouth.TechTalentBlog.BlogPost;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techtalentsouth.TechTalentBlog.BlogPostRepository;
import com.techtalentsouth.TechTalentBlog.BlogPost.BlogPost;

@Controller
public class BlogPostController {
	
    @Autowired
    private BlogPostRepository blogPostRepository;
    
	//private static List<BlogPost> posts = FakeBlogs.makeFakeBlogs();
    
	@GetMapping(value= "/")
    public String index(BlogPost blogPost, Model model) {
	List<BlogPost> posts = blogPostRepository.findAll();
	
		model.addAttribute("posts", posts);
	    return "blogpost/index";
	}
 
    @GetMapping(value= "/blogpost/new")
    public String newBlog (BlogPost blogPost) {
    	return "blogpost/new";
		
    }
    
    @PostMapping(value = "/blogpost/new")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
	//saves the new blogpost item to the database
    blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry()));
	
	//populating result.html with the blogpost info
	model.addAttribute("title", blogPost.getTitle());
	model.addAttribute("author", blogPost.getAuthor());
	model.addAttribute("blogEntry", blogPost.getBlogEntry());
	model.addAttribute("id", blogPost.getId());
	return "blogpost/result";
    }
    
 //FINDBYAUTHOR
    @PostMapping(value = "/blogpost/index") //change to author?
    public String searchByAuthor(String author, BlogPost blogPost, Model model) {
    	List <BlogPost> posts = blogPostRepository.findByAuthorIgnoreCase(author);
    	System.out.println(author);
    	
    	model.addAttribute("posts", posts);	
    	
    	return "blogpost/index";
    
    }
    
//DELETE    
    @RequestMapping(value = "/blogpost/{id}", method =  RequestMethod.POST)
	public String deletePostWithId(@PathVariable("id") long id, BlogPost blogPost, Model model) {
		
    	System.out.println("Delete is happening.");
		System.out.println(id);		
		blogPostRepository.deleteById(id);
		
		List<BlogPost> posts = blogPostRepository.findAll();
		model.addAttribute("posts", posts);
		
		return "blogpost/index"; 
	}
    
 //EDIT   
    @PostMapping(value= "/blogpost/edit/{id}")
    public String editPostWithId(@PathVariable("id") Long id, BlogPost blogPost, Model model) {
    	System.out.printf("Edit Route: ID: %s\n", id);	
    	
     	BlogPost post = blogPostRepository.findById(id).orElse(null);
     		if(post != null)  {
     			System.out.println(post.getId());
     			model.addAttribute(post);
     			
     		}else {
			System.out.printf("Could not find post at %sn", id);
			}
  
     		return "blogpost/edit";
    		}
    
    @PostMapping("/blogpost/edit")
    public String updateBlogPost(Long id, BlogPost blog, Model model) {
    	System.out.println(id);
    	System.out.println(blog.getTitle());
    	System.out.println(blog.getAuthor());
    	System.out.println(blog.getBlogEntry());
    	
    	BlogPost update = blogPostRepository.findById(id).orElse(null);
    	if(update !=null) {
    		update.setAuthor(blog.getAuthor());
    		update.setAuthor(blog.getTitle());
    		update.setAuthor(blog.getBlogEntry());
    		blogPostRepository.save(update);	 
    	} else {
    		System.out.printf("Could not find post at %sn", id);
    	}
    	
    	model.addAttribute("title", update.getTitle());
    	model.addAttribute("author", update.getAuthor());
    	model.addAttribute("blogEntry", update.getBlogEntry());
    	model.addAttribute("id", update.getId());
    	
    	return "blogpost/result";
    } 
    
}




//comment out="/** */"

