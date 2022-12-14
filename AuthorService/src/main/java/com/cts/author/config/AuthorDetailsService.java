package com.cts.author.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.author.dao.AuthorDAO;
import com.cts.author.entity.Author;

@Service
public class AuthorDetailsService implements UserDetailsService{
	
	@Autowired
	private AuthorDAO dao;
    
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("AUTHOR"));
		Author author = dao.findByEmail(username);
		return new User(author.getEmail(),author.getPwd(),authorities);
	}

}
