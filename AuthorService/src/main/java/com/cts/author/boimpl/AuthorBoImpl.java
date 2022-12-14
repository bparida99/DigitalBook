package com.cts.author.boimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.author.bo.AuthorBo;
import com.cts.author.dao.AuthorDAO;
import com.cts.author.entity.Author;
import com.cts.author.exception.DigitalBooksException;

@Service
public class AuthorBoImpl implements AuthorBo{
	
	@Autowired
	private AuthorDAO dao;
	

	@Override
	public Author getAuthorById(Long id) throws DigitalBooksException {
		Author author = dao.findById(id).get();
		if(author==null) { 
			throw new DigitalBooksException("Author not found with ID: "+id);
		}
		return author;
	}

	@Override
	public Author getAuthorByIdAndPwd(String emailId, String pwd) throws DigitalBooksException {
		Author author = dao.findByEmailAndPwd(emailId, pwd);
		if(author!=null) {
			throw new DigitalBooksException("Author authentication failed");
		}
		return author;
	}

	@Override
	public Author createAuthor(Author author) {
		return dao.saveAndFlush(author);
	}

	@Override
	public Author updateAuthor(Author author) throws DigitalBooksException {
		getAuthorById(author.getAuthorId());
		return dao.saveAndFlush(author);
	}

	@Override
	public Author findByAuthorEmail(String email) throws DigitalBooksException {
		return dao.findByEmail(email);
	}

}
