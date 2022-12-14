package com.cts.book.boimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.book.bo.BookBo;
import com.cts.book.dao.BookDAO;
import com.cts.book.enitity.Book;
import com.cts.book.exception.DigitalBooksException;

@Service
public class BookBoImpl implements BookBo{

	@Autowired
	private BookDAO dao;
	
	@Override
	public Book createBook(Book book) {
		return dao.saveAndFlush(book);
	}

	@Override
	public Book updateBook(Book book) throws DigitalBooksException {
        findById(book.getBookId());
		return dao.saveAndFlush(book);
	}

	@Override
	public Book deleteBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAllBook() {
		return dao.findAll();
	}

	@Override
	public List<Book> findByBookName(String name) {
		return dao.findByBookNameLike(name);
	}

	@Override
	public Book findById(Long id) throws DigitalBooksException {
		Book book = dao.findById(id).get();
		if(book==null) {
			throw new DigitalBooksException("Book not found with id: "+id);
		}
		return book;
	}

	@Override
	public List<Book> findBookByAuthor(Long authorId) {
		return dao.findByAuthorId(authorId);
	}
	
	

}
