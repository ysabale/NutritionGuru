package com.nutrition.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nutrition.authentication.JwtTokenUtil;
<<<<<<< HEAD
import com.nutrition.config.UnauthorizedUser;
import com.nutrition.model.LoginDetails;
import com.nutrition.service.LoginService;



=======
import com.nutrition.exception.UnauthorizedUser;
import com.nutrition.model.LoginDetails;
import com.nutrition.service.LoginService;

>>>>>>> b9d8f793d11dfa9cce97cf702482c5b929659409
@RestController
public class LoginController {

	static Logger logger = Logger.getLogger(LoginController.class.getName());

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	/*
	 * This method is used for first time user registration
	 */
	@PostMapping("/userCreation")
	public LoginDetails signUp(@RequestBody LoginDetails userDetails) {
		return loginService.signUp(userDetails);
	}

	/*
	 * This method is used for user login
	 */
	@PostMapping("/login")
	public LoginDetails signIn(@RequestBody LoginDetails loginDetails, HttpServletResponse response) throws Exception {
		LoginDetails userData = null;
		authenticate(loginDetails.getEmailId(), loginDetails.getPassword());
		final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(loginDetails.getEmailId());
		final String token = jwtTokenUtil.generateToken(userDetails);
		response.addHeader("Authorization", "Bearer " + token);
		if (token != null) {
			userData = loginService.signIn(loginDetails);
			userData.setToken(token);
		} else {
			logger.error("Unauthorized User!");
			throw new UnauthorizedUser();
		}
		return userData;
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			logger.error("Invalid Credentials!");
			throw new UnauthorizedUser();

		}
	}
}
