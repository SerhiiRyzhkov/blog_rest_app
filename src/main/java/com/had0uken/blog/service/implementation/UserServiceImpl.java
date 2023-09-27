package com.had0uken.blog.service.implementation;

import com.had0uken.blog.access.Access;
import com.had0uken.blog.model.user.Role;
import com.had0uken.blog.model.user.User;
import com.had0uken.blog.payload.responses.ApiResponse;
import com.had0uken.blog.payload.responses.ContentResponse;
import com.had0uken.blog.payload.responses.Response;
import com.had0uken.blog.repository.UserRepository;
import com.had0uken.blog.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements CustomUserDetailsService {
    private final UserRepository userRepository;

    private final Access access;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    @Override
    public Response getPostsByUser(String username) {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new ContentResponse<>(List.of(user.getPosts()), HttpStatus.OK);
        } else return new ApiResponse("User was not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public Response getStoriesByUser(String username) {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new ContentResponse<>(List.of(user.getStories()), HttpStatus.OK);
        } else return new ApiResponse("User was not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public Response addNewUser(User newUser, Authentication authentication, PasswordEncoder passwordEncoder) {
        if (!access.addUserAccess(authentication))
            return new ApiResponse("You do not have permission to create a new user", HttpStatus.FORBIDDEN);
        Optional<User> userOptional = userRepository.findByEmail(newUser.getEmail());
        if (userOptional.isPresent()) {
            return new ApiResponse("User with this email already exists", HttpStatus.CONFLICT);
        } else {
            newUser.setRole(Role.USER);
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userRepository.save(newUser);
            return new ApiResponse("User was created", HttpStatus.OK);
        }


    }

    @Override
    public Response updateUser(String username, User updUser, Authentication authentication, PasswordEncoder passwordEncoder) {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            User updater = userRepository.findByEmail(authentication.getName()).get();

            User existingUser = userOptional.get();
            if (!access.updateUserAccess(updater, existingUser))
                return new ApiResponse("You do not have permission to update this user", HttpStatus.FORBIDDEN);

            if (updUser.getFirstname() != null) existingUser.setFirstname(updUser.getFirstname());
            if (updUser.getLastname() != null) existingUser.setLastname(updUser.getLastname());
            if (updUser.getRole() != null) existingUser.setRole(updUser.getRole());
            if (updUser.getEmail() != null) existingUser.setEmail(updUser.getEmail());
            if (updUser.getPassword() != null) existingUser.setPassword(passwordEncoder.encode(updUser.getPassword()));
            userRepository.save(existingUser);
            return new ApiResponse("User was updated", HttpStatus.OK);
        } else
            return new ApiResponse("User was not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public Response deleteUser(String username, Authentication authentication) {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (!access.deleteUserAccess(existingUser, userRepository.findByEmail(authentication.getName()).get()))
                return new ApiResponse("You do not have permission to delete this user", HttpStatus.FORBIDDEN);
            userRepository.delete(existingUser);
            return new ApiResponse("User was deleted", HttpStatus.OK);
        } else
            return new ApiResponse("User was not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public Response setUserRole(String username, Authentication authentication, Role role) {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (!access.setRole(userRepository.findByEmail(authentication.getName()).get()))
                return new ApiResponse("You do not have permission to change Role", HttpStatus.FORBIDDEN);
            existingUser.setRole(role);
            userRepository.save(existingUser);
            return new ApiResponse(existingUser.getEmail() + " assigned as " + existingUser.getRole(), HttpStatus.OK);
        } else
            return new ApiResponse("User was not found", HttpStatus.NOT_FOUND);
    }


}