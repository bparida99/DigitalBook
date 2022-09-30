package com.cts.author.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.author.entity.Author;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Long>{
	
	
	public Author findByEmailAndPwd(String email,String pwd);
	
	public Author findByEmail(String email);
	

}
