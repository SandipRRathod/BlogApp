package com.blog_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog_app.entitys.Blog;

@Repository 
public interface BlogRepo extends JpaRepository<Blog, Long>{ //// Extends JpaRepository to handle CRUD operations
	//This makes database interaction simple & efficient, as we don’t have to write queries manually.
}
