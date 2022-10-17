package com.cts.author.bo;

import com.cts.author.entity.Author;
import com.cts.author.exception.DigitalBooksException;

public interface AuthorBo {
	
	public Author getAuthorById(Long id) throws DigitalBooksException;
	
	public Author getAuthorByIdAndPwd(String emailId,String pwd) throws DigitalBooksException;
	
	public Author createAuthor(Author author);
	
	public Author updateAuthor(Author author) throws DigitalBooksException;
	
	public Author findByAuthorEmail(String email) throws DigitalBooksException;
	

}
