package com.cts.reader;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cts.reader.bo.ReaderBo;
import com.cts.reader.entity.Reader;
import com.cts.reader.exception.DigitalBooksException;
import com.cts.reader.repo.ReaderDAO;

@SpringBootTest(classes= ReaderServiceApplication.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReaderServiceApplicationTests {

	@Autowired
	private ReaderBo bo;
	
	@MockBean
	private ReaderDAO dao;
	
	@Test
	void findReaderByIdTest() throws DigitalBooksException {
		
		Optional<Reader> reader = Optional.ofNullable(new Reader(1l, "Biswo", "biswo@gmail.com", "9938864907", "biswojit"));
		when(dao.findById(1l)).thenReturn(reader);
		
		Reader reader1= bo.findReaderById(1l);
		assertNotNull(reader1);
	}
	
	@Test
	public void findReaderByEmailAndPwdTest() {
		Reader reader = new Reader(1l, "Biswo", "biswo@gmail.com", "9938864907", "biswojit");
		when(dao.findByReaderEmailAndPwd("biswo@gmail.com", "biswojit")).thenReturn(reader);
		Reader reader1 = bo.findReaderByEmailAndPwd("biswo@gmail.com", "biswojit");
		assertNotNull(reader1);
	}
	
	

}
