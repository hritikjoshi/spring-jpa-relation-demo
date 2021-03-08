package com.bd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd.model.Tag;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	 Optional<Tag> findByName(String name);
}
