package com.had0uken.blog.repository;

import com.had0uken.blog.model.post.Stories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoriesRepository extends JpaRepository<Stories,Long> {
}
