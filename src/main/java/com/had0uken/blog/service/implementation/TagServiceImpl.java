package com.had0uken.blog.service.implementation;

import com.had0uken.blog.model.Tag;
import com.had0uken.blog.repository.TagRepository;
import com.had0uken.blog.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

}
