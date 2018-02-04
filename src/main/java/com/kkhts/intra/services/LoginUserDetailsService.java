package com.kkhts.intra.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kkhts.intra.dao.UserRepository;
import com.kkhts.intra.entity.LoginUserDetails;
import com.kkhts.intra.entity.User;

@Service
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository rep;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = rep.findByMailAddress(username);
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found...");
		}
		return new LoginUserDetails(user);
	}

}