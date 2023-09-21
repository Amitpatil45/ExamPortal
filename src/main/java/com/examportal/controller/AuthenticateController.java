package com.examportal.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.config.javaUtils;
import com.examportal.model.JWTRequest;
import com.examportal.model.JWTResponse;

@RestController
@CrossOrigin("*")
public class AuthenticateController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private com.examportal.services.implement.userDetailServiceImpl userDetailServiceImpl;

    @Autowired
    private javaUtils jwtutils;


    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {

        try
        {

            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());


        }
        catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("User Not found");
        }

        UserDetails userDetails=this.userDetailServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token=this.jwtutils.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse());

    }

    private  void  authenticate(String username,String password) throws Exception {

        try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));


        }
        catch (DisabledException e)
        {
            throw  new Exception("User disabled");
        }
        catch (BadCredentialsException e)
        {
            throw  new Exception("Invalid Credentials"+e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public UserDetails getCurrentUser(Principal principal)
    {
        return this.userDetailServiceImpl.loadUserByUsername(principal.getName());
    }

}

