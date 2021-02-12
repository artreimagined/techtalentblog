package com.techtalentsouth.TechTalentBlog.BlogPost;

import java.util.ArrayList;
import java.util.List;

public class FakeBlogs {
	private static List<BlogPost> blogPosts;
				
	//method to (populate) blogPosts with fake blog data
	public static List<BlogPost> makeFakeBlogs() {
		blogPosts = new ArrayList<>();
		return blogPosts;
	}
	//return the list of blogPosts
	public static List<BlogPost> allBlogs() {

		return blogPosts;
	}

	//return blogpost obj matching id or log 'id not found' & return null
	public static BlogPost getBlogById(long id) {
		for(BlogPost post : blogPosts) {
			if(post.getId()==id) {
				return post;
				}
			}
		System.out.println("Id not found");
		return null;
	}
	
	public static void addNewPost(BlogPost newPost) {
		blogPosts.add(newPost);
	}
	
	public static long createId() {
		//create unique id
		System.out.println("createId started");
		int listSize = blogPosts.size();
		System.out.println(listSize);
		
		BlogPost lastPost = blogPosts.get(listSize-1);
		long id = lastPost.getId()+1;
		System.out.println(lastPost.getId());
		System.out.println(id);
		return id;
	
	}
	
	//try to refactor to make better?
	//update post values and return post or null when complete
	public static BlogPost updateBlogPost(long id, String title, String author, String blogEntry) {
		for(BlogPost post : blogPosts) {
			if(post.getId() == id) {
				int postIndex = blogPosts.indexOf(post);
				post.setTitle(title);
				post.setAuthor(author);
				post.setBlogEntry(blogEntry);
				blogPosts.set(postIndex, post);
				return post;
			}
		}
		System.out.println("No such posts exists to update");
		return null; 
	}
	
	public static boolean deletePost(long id) {
		
		System.out.println("Fake Blogs Delete");
		int postIndex = -1;
		for(BlogPost post : blogPosts) {
			if(post.getId() == id) {
				postIndex = blogPosts.indexOf(post);
				continue;
			}
		}
		if(postIndex>-1) {
			blogPosts.remove(postIndex);
		}
		return true;
	}
	
	
	public static List<BlogPost> getBlogByAuthor (String author) {
		System.out.println("search &s\n");
		List<BlogPost> authorList = new ArrayList<BlogPost>();
		for(BlogPost post : blogPosts) {
			if(post.getAuthor() .equalsIgnoreCase(author)) {
				authorList.add(post);	
			}
		}
		if (authorList.size() > 0) {
			System.out.println(authorList);
			return authorList;
		}
		else {
			System.out.println("Author Not Found");
			return null;
		}
			
	}
	
}

