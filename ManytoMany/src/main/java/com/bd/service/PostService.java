package com.bd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bd.model.Post;
import com.bd.model.Tag;
import com.bd.repository.PostRepository;
import com.bd.repository.TagRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;

	@Autowired
	TagRepository tagRepository;

	public ResponseEntity<Object> createPost(Post model) {
		Post post = new Post();
		if (postRepository.findByEmail(model.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("The Email is already Present, Failed to Create new User");
		} else {
			post.setContent(model.getContent());
			post.setDescription(model.getDescription());
			post.setEmail(model.getEmail());
			post.setTitle(model.getTitle());

			// check tag if exists

			List<Tag> newTagList = new ArrayList<>();

			List<Tag> tagList = model.getTags();
			for (Tag tag : tagList) {
				Optional<Tag> opTag = tagRepository.findByName(tag.getName());
				if (opTag.isPresent()) {
					newTagList.add(opTag.get());
				} else {
					Tag newSaved = tagRepository.save(tag);
					newTagList.add(newSaved);
				}
			}
			post.setTags(newTagList);

			
			  Post savedPost = postRepository.save(post); 
			  if(postRepository.findById(savedPost.getId()).isPresent()) 
				  return ResponseEntity.ok("User Created Successfully"); 
			  else return ResponseEntity.unprocessableEntity().body("Failed Creating User as Specified");
			 
		}
	}

	@Transactional
	public ResponseEntity<Object> updatePost(Post post, Long id) {
		if (postRepository.findById(id).isPresent()) {
			Post newPost = postRepository.findById(id).get();
			post.setContent(post.getContent());
			post.setDescription(post.getDescription());
			post.setEmail(post.getEmail());
			post.setTitle(post.getTitle());

			post.setTags(post.getTags());
			Post savedPost = postRepository.save(newPost);
			if (postRepository.findById(savedPost.getId()).isPresent())
				return ResponseEntity.accepted().body("User updated successfully");
			else
				return ResponseEntity.unprocessableEntity().body("Failed updating the user specified");
		} else
			return ResponseEntity.unprocessableEntity().body("Cannot find the user specified");
	}

	public ResponseEntity<Object> deletePost(Long id) {
		if (postRepository.findById(id).isPresent()) {
			postRepository.deleteById(id);
			if (postRepository.findById(id).isPresent())
				return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified User");
			else
				return ResponseEntity.ok().body("Successfully deleted the specified user");
		} else
			return ResponseEntity.badRequest().body("Cannot find the user specified");
	}

	public Optional<Post> getPostById(Long id) {

		return postRepository.findById(id);
	}

	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}
}
