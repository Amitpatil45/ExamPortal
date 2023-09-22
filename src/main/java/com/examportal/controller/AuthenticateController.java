package com.examportal.controller;

import java.security.Principal;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
import com.examportal.model.User;
import com.examportal.repo.userRepository;
import com.examportal.services.implement.*;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private userDetailServiceImpl userDetailServiceImpl;

	@Autowired
	private userRepository userRepository;

	@Autowired
	private javaUtils jwtutils;

	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {

		try {
				org.springframework.security.core.Authentication authentication = authenticationManager.authenticate
						(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
				User user = (User) authentication.getPrincipal();
				String token = this.jwtutils.generateToken(user);
				JWTResponse Response = new JWTResponse(token,user.getUsername(),user.getfName(),user.getLname(),user.getEmail(),user.getPhone(),user.getProfile());
				return ResponseEntity.ok().body(Response);

		}catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials" + e.getMessage());
		}
	
		
		
	/*	try {

			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User Not found");
		}

		UserDetails userDetails = this.userDetailServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtutils.generateToken(userDetails);
		return ResponseEntity.ok(new JWTResponse(token));

	}

	

	private void authenticate(String username, String password) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			throw new Exception("User disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials" + e.getMessage());
		}*/
	}

	@GetMapping("/current-user")
	public UserDetails getCurrentUser(Principal principal) {
		return this.userDetailServiceImpl.loadUserByUsername(principal.getName());
	}

}
