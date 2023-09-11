package com.had0uken.blog.repository;

import com.had0uken.blog.model.post.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<MediaFile,Long> {
}
