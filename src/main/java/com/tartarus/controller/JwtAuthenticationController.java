package com.tartarus.controller;

import com.tartarus.config.JwtTokenUtil;
import com.tartarus.dao.UserDao;
import com.tartarus.model.JwtRequest;
import com.tartarus.model.JwtResponse;
import com.tartarus.model.UserDTO;
import com.tartarus.service.JwtUserDetailsService;
import com.tartarus.util.LoginRegex;
import com.tartarus.util.UserErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private LoginRegex regex;
	@Autowired
	private UserErrorHandler userErrorHandler;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {

		ResponseEntity<?> response;

		if(!regex.checkPatternEmail(user.getUsername()) || !regex.checkPasswordPattern(user.getPassword())){
			response = userErrorHandler.regexFormatIsWrong();
		}else if(userDao.findByUsername(user.getUsername()) != null){
			response = userErrorHandler.credidentialIsNotFound();
		}else{
			response = ResponseEntity.ok(userDetailsService.save(user));
		}
		return response;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}