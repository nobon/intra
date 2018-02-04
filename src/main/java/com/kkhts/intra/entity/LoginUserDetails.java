package com.kkhts.intra.entity;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;


public class LoginUserDetails extends org.springframework.security.core.userdetails.User {

	private final User user;

	public LoginUserDetails(User user) {
		super(user.getUsername(), user.getPassword(), new ArrayList<GrantedAuthority>());
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
