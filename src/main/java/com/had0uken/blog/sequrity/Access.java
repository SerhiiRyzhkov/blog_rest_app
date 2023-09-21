package com.had0uken.blog.sequrity;

import com.had0uken.blog.model.post.Comment;
import com.had0uken.blog.model.post.Post;
import com.had0uken.blog.model.post.Stories;
import com.had0uken.blog.model.post.UserOwned;
import com.had0uken.blog.model.user.Role;
import com.had0uken.blog.model.user.User;
import com.had0uken.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component

@RequiredArgsConstructor
public class Access {

    private final UserRepository userRepository;


    public boolean deleteCheckAccess(UserOwned userOwned, Authentication authentication)
    {
        User user = userRepository.findByEmail(authentication.getName()).get();
        return ((userOwned.getUser().equals(user))||(user.getAuthorities().contains(Role.ADMIN))
                ||(user.getAuthorities().contains(Role.MODERATOR)));
    }

    public boolean editCheckAccess(UserOwned userOwned, Authentication authentication)
    {
        User user = userRepository.findByEmail(authentication.getName()).get();
        return ((userOwned.getUser().equals(user))||(user.getAuthorities().contains(Role.ADMIN))
                ||(user.getAuthorities().contains(Role.MODERATOR)));
    }



}
