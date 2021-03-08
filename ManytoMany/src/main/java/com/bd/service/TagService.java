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
public class TagService {
	@Autowired
	PostRepository postRepository;

	@Autowired
	TagRepository tagRepository;

	@Transactional
	public ResponseEntity<Object> addTag(Tag tag) {

		Tag newTag = new Tag();
		newTag.setId(tag.getId());
		newTag.setName(tag.getName());
		List<Tag> tagList = new ArrayList<>();
		tagList.add(newTag);
		for (int i = 0; i < tag.getPosts().size(); i++) {
			if (!postRepository.findByEmail(tag.getPosts().get(i).getEmail()).isPresent()) {
				Post newPost = tag.getPosts().get(i);
				newPost.setTags(tagList);
				Post savedPost = postRepository.save(newPost);
				if (!tagRepository.findById(savedPost.getId()).isPresent())
					return ResponseEntity.unprocessableEntity().body("Tag Creation Failed");
			} else
				return ResponseEntity.unprocessableEntity().body("User with email Id is already Present");
		}
		return ResponseEntity.ok("Successfully created Post");
	}
	
	public ResponseEntity<Object> deleteTag(Long id) {
		if (tagRepository.findById(id).isPresent()) {
			if (tagRepository.getOne(id).getPosts().size() == 0) {
				tagRepository.deleteById(id);
				if (tagRepository.findById(id).isPresent()) {
					return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
				} else
					return ResponseEntity.ok().body("Successfully deleted specified record");
			} else
				return ResponseEntity.unprocessableEntity()
						.body("Failed to delete,  Please delete the users associated with this tag");
		} else
			return ResponseEntity.unprocessableEntity().body("No Records Found");
	}

	public ResponseEntity<Object> updateTag(Long id, Tag tag) {
		if (tagRepository.findById(id).isPresent()) {
			Tag newTag = tagRepository.findById(id).get();
			newTag.setName(tag.getName());
			newTag.setId(tag.getId());
			Tag savedTag = tagRepository.save(newTag);
			if (tagRepository.findById(savedTag.getId()).isPresent())
				return ResponseEntity.accepted().body("Tag saved successfully");
			else
				return ResponseEntity.badRequest().body("Failed to update Tag");

		} else
			return ResponseEntity.unprocessableEntity().body("Specified Role not found");
	}

	public Optional<Tag> getTagById(Long id) {

		return tagRepository.findById(id);
	}

	public List<Tag> getAllTags() {

		return tagRepository.findAll();
	}
}





