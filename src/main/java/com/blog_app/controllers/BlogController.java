package com.blog_app.controllers;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_app.entitys.Blog;
import com.blog_app.services.AIService;
import com.blog_app.services.BlogService;

@RestController // Marks this class as a REST controller, handling HTTP requests and returning JSON responses
@CrossOrigin // Allows cross-origin requests (useful for frontend-backend communication)
@RequestMapping("/api")
public class BlogController {

	@Autowired
	private BlogService blogService; // Injects BlogService for blog-related operations
	
	@Autowired
	private AIService aiService; // Injects AIService for AI-based blog summarization

	
	@PostMapping("/addblog") // Endpoint to add a new blog
	public ResponseEntity<Blog> saveBlog(@Valid @RequestBody Blog blog) {
		return ResponseEntity.status(HttpStatus.CREATED).body(blogService.addBlog(blog)); // Saves and returns the blog
	}

	
	@DeleteMapping("/deleteblog/{id}") // Endpoint to delete a blog by ID
	public ResponseEntity<?> deleteBlog(@PathVariable long id) {
		blogService.deleteBlog(id);
		return ResponseEntity.ok("Blog Deleted Successfully.");
	}

	
	@PutMapping("/updateblog/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable long id,@Valid @RequestBody Blog blog) {
	    Blog updatedBlog = blogService.updateBlog(id, blog)
	            .orElseThrow(() -> new NoSuchElementException("Blog Not Found With Id: " + id));

	    return ResponseEntity.ok(updatedBlog);
	}

	@GetMapping("/getblog/{id}") // Endpoint to get a single blog by ID
	public ResponseEntity<Blog> getBlog(@PathVariable long id) {
	    Blog blog = blogService.getBlog(id)
	            .orElseThrow(() -> new NoSuchElementException("Blog Not Found With Id: " + id));

	    return ResponseEntity.ok(blog);
	}
	
	@GetMapping("/getallblog/{pageno}") // Endpoint to get all blogs with pagination
	public ResponseEntity<Page<Blog>> getAllBlog(@PathVariable int pageno) {
		return ResponseEntity.ok(blogService.getAllBlog(pageno));
	}

	
	@GetMapping("/ai/summarize") // open ai blog summarization endpoint
	public String askChatGpt(@RequestBody String content) {
		return aiService.askOpenAI(content); // Calls OpenAI to summarize the blog content
	}
	
	@GetMapping("/ai/summarize{id}") // open ai blog summarization endpoint
	public String askOpenAi(@PathVariable long id) {
		Blog blog = blogService.getBlog(id)
	            .orElseThrow(() -> new NoSuchElementException("Blog Not Found With Id: " + id));
		return aiService.askOpenAI(blog.getContent()); // Calls OpenAI to summarize the blog content
	}
}
