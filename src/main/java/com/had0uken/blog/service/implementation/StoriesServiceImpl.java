package com.had0uken.blog.service.implementation;


import com.had0uken.blog.model.post.Stories;
import com.had0uken.blog.model.user.User;
import com.had0uken.blog.payload.responses.ApiResponse;
import com.had0uken.blog.payload.responses.ContentResponse;
import com.had0uken.blog.payload.responses.Response;
import com.had0uken.blog.repository.MediaRepository;
import com.had0uken.blog.repository.StoriesRepository;
import com.had0uken.blog.repository.TagRepository;
import com.had0uken.blog.repository.UserRepository;
import com.had0uken.blog.access.Access;
import com.had0uken.blog.service.StoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoriesServiceImpl implements StoriesService {

    private final StoriesRepository storiesRepository;
    private final UserRepository userRepository;
    private final MediaRepository mediaRepository;
    private final TagRepository tagRepository;
    private final Access access;

    @Override
    public Response getAllStories() {
        return new ContentResponse<>(storiesRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public Response getStories(Long id) {
        Optional<Stories> optional = storiesRepository.findById(id);
        if (optional.isPresent())
            return new ContentResponse<>(List.of(optional.get()), HttpStatus.OK);
        else return new ApiResponse("Stories not found", HttpStatus.NOT_FOUND);
    }


    @Override
    public Response addNewStories(Stories stories, Authentication authentication) {
        mediaRepository.save(stories.getMediaFile());
        User user = userRepository.findByEmail(authentication.getName()).get();
        stories.setUser(user);
        tagRepository.saveAll(stories.getTags());
        storiesRepository.save(stories);
        stories.setCreated(LocalDate.now());
        storiesRepository.save(stories);
        return new ApiResponse("Stories was created", HttpStatus.CREATED);
    }


    @Override
    public Response deleteStories(Long id, Authentication authentication) {
        Optional<Stories> optional = storiesRepository.findById(id);
        if (optional.isPresent()) {
            Stories existingStories = optional.get();
            if (!access.editCheckAccess(existingStories, authentication))
                return new ApiResponse("You do not have permission to delete this stories", HttpStatus.FORBIDDEN);
            storiesRepository.delete(existingStories);

            return new ApiResponse("Stories deleted successfully", HttpStatus.NO_CONTENT);
        } else return new ApiResponse("Stories was not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public Response likeStories(Long id, Authentication authentication) {
        Optional<Stories> optional = storiesRepository.findById(id);
        if (optional.isPresent()) {
            Stories existingStories = optional.get();
            User user = userRepository.findByEmail(authentication.getName()).get();
            Response response;
            if (!existingStories.getLikedByUsers().contains(user)) {
                existingStories.getLikedByUsers().add(user);
                user.getLikedStories().add(existingStories);
                response = new ApiResponse("Stories liked successfully", HttpStatus.OK);
            } else {
                existingStories.getLikedByUsers().remove(user);
                user.getLikedStories().remove(existingStories);
                response = new ApiResponse("Stories unliked successfully", HttpStatus.OK);
            }
            userRepository.save(user);
            storiesRepository.save(existingStories);
            return response;
        } else return new ApiResponse("Stories was not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public Response repostedStories(Long id, Authentication authentication) {
        Optional<Stories> optional = storiesRepository.findById(id);
        if (optional.isPresent()) {
            Stories existingStories = optional.get();
            User user = userRepository.findByEmail(authentication.getName()).get();
            Response response;
            existingStories.getRepostedByUsers().add(user);
            user.getRepostedStories().add(existingStories);
            response = new ApiResponse("Stories reposted successfully", HttpStatus.OK);
            userRepository.save(user);
            storiesRepository.save(existingStories);
            return response;
        } else return new ApiResponse("Stories was not found", HttpStatus.NOT_FOUND);
    }


}
