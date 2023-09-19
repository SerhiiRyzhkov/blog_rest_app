package com.had0uken.blog.repository;

import com.had0uken.blog.model.post.Post;
import com.had0uken.blog.model.post.Stories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoriesRepository extends JpaRepository<Stories,Long> {
    @Query("SELECT p FROM Post p JOIN p.tags t WHERE t.name = :tagName")
    List<Stories> findByTagName(String tagName);
}
