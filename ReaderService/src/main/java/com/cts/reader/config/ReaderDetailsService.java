package com.cts.reader.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.reader.entity.Reader;
import com.cts.reader.repo.ReaderDAO;

@Service
public class ReaderDetailsService implements UserDetailsService{
	
	@Autowired
	private ReaderDAO dao;
    
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("READER"));
		Reader reader = dao.findByReaderEmail(username);
		return new User(reader.getReaderEmail(),reader.getPwd(),authorities);
	}

}
