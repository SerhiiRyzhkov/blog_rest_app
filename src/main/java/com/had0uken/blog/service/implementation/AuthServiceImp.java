package com.had0uken.blog.service.implementation;

import com.had0uken.blog.model.user.Role;
import com.had0uken.blog.model.user.User;
import com.had0uken.blog.payload.responses.ApiResponse;
import com.had0uken.blog.payload.responses.Response;
import com.had0uken.blog.repository.UserRepository;
import com.had0uken.blog.payload.requests.AuthRequest;
import com.had0uken.blog.payload.responses.AuthResponse;
import com.had0uken.blog.payload.requests.RegisterRequest;
import com.had0uken.blog.service.AuthService;
import com.had0uken.blog.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public Response register(RegisterRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if(optionalUser.isPresent()){
            return new ApiResponse("Email address is already registered.", HttpStatus.CONFLICT);
        }
        else {
            User user = User.builder().firstname(request.getFirstname()).lastname(request.getLastname())
                    .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).
                    role(Role.USER).build();

            userRepository.save(user);
            return new ApiResponse("Registration success", HttpStatus.OK);
        }
    }



    @Override
    public Response authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public PasswordEncoder getEncoder() {
        return passwordEncoder;
    }
}