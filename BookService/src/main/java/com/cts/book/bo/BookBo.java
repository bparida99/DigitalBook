package com.cts.book.bo;

import java.util.List;

import com.cts.book.enitity.Book;
import com.cts.book.exception.DigitalBooksException;

public interface BookBo {
	
	public Book createBook(Book book);
	
	public Book findById(Long id) throws DigitalBooksException;
	
	public Book updateBook(Book book) throws DigitalBooksException;
	
	public Book deleteBook(Book book);
	
	public List<Book> getAllBook();
	
	public List<Book> findByBookName(String name);
	
	public List<Book> findBookByAuthor(Long authorId);
	
	

}
