package com.blog_app.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blog_app.entitys.Blog;
import com.blog_app.repos.BlogRepo;

@Service // Marks this class as a service component in Spring (business logic layer)
public class BlogService {

    @Autowired // Injects the BlogRepo dependency automatically
    private BlogRepo blogRepo;

    // Adds a new blog post and sets the creation timestamp
    public Blog addBlog(Blog blog) {
        blog.setCreatedAt(LocalDateTime.now()); // Sets the current time as creation time
        return blogRepo.save(blog); // Saves the blog to the database
    }

    // Deletes a blog post by its ID
    public void deleteBlog(long id) {
        blogRepo.deleteById(id);
    }

    // Updates an existing blog post if found
    public Optional<Blog> updateBlog(long id, Blog blog) {
        Optional<Blog> b = blogRepo.findById(id); // Checks if the blog exists

        if (b.isPresent()) { // If blog exists, update it
            Blog updateBlog = b.get();
            updateBlog.setTitle(blog.getTitle());
            updateBlog.setContent(blog.getContent());
            updateBlog.setAuthor(blog.getAuthor());
            updateBlog.setCreatedAt(LocalDateTime.now()); // Updates timestamp
            return Optional.of(blogRepo.save(updateBlog)); // Saves updated blog
        }

        return Optional.empty(); // Returns empty if blog not found
    }

    // Retrieves a single blog post by its ID
    public Optional<Blog> getBlog(long id) {
        return blogRepo.findById(id);
    }

    // Retrieves paginated list of blog posts (5 per page) 
    //Page<T> is an interface in Spring Data JPA that provides pagination support
    public Page<Blog> getAllBlog(int no) {
        return blogRepo.findAll(PageRequest.of(no, 5)); //PageRequest.of(no, 5)  no → The page number (0-based index).5 → The number of blog posts per page.
    }
}
