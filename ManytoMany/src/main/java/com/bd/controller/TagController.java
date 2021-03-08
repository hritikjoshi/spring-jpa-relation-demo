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
import org.springframework.web.bind.annotation.RestController;

import com.bd.model.Tag;
import com.bd.service.TagService;


@RestController
public class TagController {
	
	@Autowired
    TagService tagService;
	

    @PostMapping("/tag/create")
    public ResponseEntity<Object> createTag(@RequestBody Tag tag) {
        return  tagService.addTag(tag);
    }
    @DeleteMapping("/tag/delete/{id}")
    public ResponseEntity<Object> deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
    }
    @GetMapping("/tag/details/{id}")
    public Tag getTag(@PathVariable Long id) {
    	Optional<Tag> opTag = tagService.getTagById(id);
        if(opTag.isPresent()) {
            return opTag.get();
        }
        else { return null;
        }
    }
    @GetMapping("/tag/all")
    public List<Tag> getTag() {
        return tagService.getAllTags();
    }
    @PutMapping("/tag/update/{id}")
    public ResponseEntity<Object> updateTags(@PathVariable Long id, @RequestBody Tag tag) {
        return tagService.updateTag(id, tag);
    }


}
    
    




