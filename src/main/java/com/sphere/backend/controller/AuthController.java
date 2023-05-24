/**
 * FileName: AuthController
 * Author: jane
 * Date: 2023/4/9 19:01
 * Description:
 * Version:
 */

package com.sphere.backend.controller;

import com.sphere.backend.entity.User;
import com.sphere.backend.payload.ApiResponse;
import com.sphere.backend.payload.JwtAuthenticationResponse;
import com.sphere.backend.payload.LoginRequest;
import com.sphere.backend.payload.RegisterRequest;
import com.sphere.backend.security.JwtTokenProvider;
import com.sphere.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Controller
@Api(tags = "auth class")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;


    @PostMapping("/auth/login")
    @ApiOperation("user login")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "usernameOrEmail",
                    value = "sarah",
                    dataType = "String",
                    paramType = "Query",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "password",
                    value = "1234567",
                    dataType = "String",
                    paramType = "Query",
                    required = true
            )
    })
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.getToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }



    @PostMapping("/auth/signup")
    @ApiOperation("user login")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "username",
                    value = "sarah",
                    dataType = "String",
                    paramType = "Query",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "email",
                    value = "sarah@mail.com",
                    dataType = "String",
                    paramType = "Query",
                    required = true
            ),
            @ApiImplicitParam(
                    name = "password",
                    value = "1234567",
                    dataType = "String",
                    paramType = "Query",
                    required = true
            )
    })
    private ResponseEntity<?> signupUser(@Valid @RequestBody RegisterRequest registerRequest){
        if(!userService.isEmailUnique(registerRequest.getEmail()) || !userService.isUsernameUnique(registerRequest.getUsername())){
            return new ResponseEntity(new ApiResponse(false, "Email Address or username already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        //create new user
        User user = new User(registerRequest.getDisplayName(), registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getPassword());
        User result = userService.registerUser(user);
        //generate jwt token
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerRequest.getUsername(),
                        registerRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.getToken(authentication);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(new JwtAuthenticationResponse(jwt));


    }
}
