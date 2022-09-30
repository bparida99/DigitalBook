package com.cts.book.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.book.enitity.Book;

@Repository
public interface BookDAO extends JpaRepository<Book, Long> {
	
	public List<Book> findByAuthorId(Long authorId);
	
	
	public List<Book> findByBookNameLike(String name);

}
