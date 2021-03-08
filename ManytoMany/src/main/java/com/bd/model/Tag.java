package com.bd.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String name;

    @ManyToMany(targetEntity = Post.class, mappedBy = "tags",
    		cascade = {CascadeType.PERSIST, 
					   CascadeType.DETACH,
					   CascadeType.MERGE,
					   CascadeType.REFRESH, } )
    @JsonIgnoreProperties("tags")
    private List<Post> posts;

    public Tag(Long id, String name, Set<Post> posts) {
		super();
		this.id = id;
		this.name = name;
	}

	public Tag() {

    }

    public Tag(String name) {
        this.name = name;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", posts=" + posts + "]";
	}
    
}