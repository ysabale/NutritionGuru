package com.nutrition.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.nutrition.model.LoginDetails;

<<<<<<< HEAD

=======
>>>>>>> b9d8f793d11dfa9cce97cf702482c5b929659409
public interface LoginService {
	public LoginDetails signUp(LoginDetails user);
	 public LoginDetails signIn(LoginDetails user);
	 UserDetails loadUserByUsername(String username);
}
