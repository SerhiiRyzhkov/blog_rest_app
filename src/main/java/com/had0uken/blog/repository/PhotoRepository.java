package com.had0uken.blog.repository;

import com.had0uken.blog.model.post.Photo;
import com.had0uken.blog.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {
}
