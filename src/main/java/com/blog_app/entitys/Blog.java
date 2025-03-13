package com.blog_app.entitys;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data // Lombok annotation to generate getters, setters, and other utility methods
@Entity // Marks this class as a JPA entity (maps to a database table)
public class Blog {

    @Id // Marks 'id' as the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generates the ID value
    private long id;
    
    @NotBlank(message = "Title is required") 
    private String title; // Stores the blog title

    private String author; // Stores the author's name

    @NotBlank(message = "Content cannot be empty")
    @Column(columnDefinition = "TEXT") // Defines 'content' as a text column in the database "TEXT" becouse Better for long content
    private String content; 

    private LocalDateTime createdAt; // Stores the blog's creation timestamp
}

