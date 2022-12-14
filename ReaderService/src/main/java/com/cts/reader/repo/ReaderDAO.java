package com.cts.reader.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.reader.entity.Reader;

@Repository
public interface ReaderDAO extends JpaRepository<Reader, Long>{

	
	public Reader findByReaderEmailAndPwd(String email,String pwd);
	
	public Reader findByReaderEmail(String email);
}
