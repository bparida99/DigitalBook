package com.cts.reader.bo;

import com.cts.reader.entity.Reader;
import com.cts.reader.exception.DigitalBooksException;

public interface ReaderBo {

	public Reader findReaderById(Long id) throws DigitalBooksException;
	
	public Reader findReaderByEmailAndPwd(String email, String pwd);
	
	public Reader addReader(Reader reader);
	
	public Reader updateReader(Reader reader) throws DigitalBooksException;
	
	public Reader findByEmail(String email) throws DigitalBooksException;
	
}
