package com.security.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.dto.UserDTO;
import com.security.models.DAOUser;
import com.security.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;
		/*
		 * if(username.equals("admin")) { roles = Arrays.asList(new
		 * SimpleGrantedAuthority("ROLE_ADMIN")); return new
		 * User("admin","$2a$10$dHT4aqYVNmiHbS1Sv1ymUuvHQ6bSW76fQR3kvk6UGOU9Y.8OXup4K",
		 * roles); }
		 * 
		 * if(username.equals("user")) { roles = Arrays.asList(new
		 * SimpleGrantedAuthority("ROLE_USER")); return new
		 * User("admin","$2a$10$WnE2ucN5Gp.9KsqTG4vKbeY6DKulSXBROQFOAIHtxv6KPtXBmFDze",
		 * roles); } throw new
		 * UsernameNotFoundException("User not found with name"+username);
		 */
		DAOUser user = userDao.findByUsername(username);
		if (user != null) {
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
			return new User(user.getUsername(), user.getPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + username);
	}
	
	
	public DAOUser save(UserDTO user) {
		DAOUser newUser = new DAOUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		return userDao.save(newUser);
	}

}
