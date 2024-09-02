package com.sih.controller;

import com.sih.configurations.CustomUserDetailService;
import com.sih.dto.UserDto;
import com.sih.exceptions.ApiException;
import com.sih.exceptions.ApiResponse;
import com.sih.payloads.JwtAuthenticationRequest;
import com.sih.payloads.JwtAuthenticationResponse;
import com.sih.security.authentication.JwtTokenHelper;
import com.sih.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailService userDetailService;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest request) {
        try {

            logger.info("login {}", request.toString());
            this.authenticate(request.getUsername(), request.getPassword());
            UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getUsername());
            String token = this.jwtTokenHelper.generateToken(userDetails);
            JwtAuthenticationResponse response = new JwtAuthenticationResponse();
            response.setToken(token);
            response.setUser(this.userService.getUserByEmail(request.getUsername()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    private void authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        try {
            this.authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException exception) {
            throw new ApiException("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            logger.info("register user dto {}", userDto.toString());
            return new ResponseEntity<>(this.userService.registerUser(userDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }
}
