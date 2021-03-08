package com.bd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bd.model.Post;
import com.bd.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
    PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Object> createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }
    
    
    @GetMapping("/all")
    public List<Post> getPosts() {
        return postService.getAllPosts();
    }
    
    @GetMapping("/details/{id}")
    public Post getPost(@PathVariable Long id) {
    	Optional<Post> opPost = postService.getPostById(id);
    	
        if(opPost.isPresent()) {
            return opPost.get();
            }
        else {
        	return  null;
        	}
    }
    
  
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(post, id);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }





}

