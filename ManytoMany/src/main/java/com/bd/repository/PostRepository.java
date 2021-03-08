package com.bd.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd.model.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	Optional<Post> findByEmail(String email);

}

