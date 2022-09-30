package com.cts.book.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.book.bo.BookBo;
import com.cts.book.enitity.Book;
import com.cts.book.exception.DigitalBooksException;

@RestController
@RequestMapping("/api/v1/digitalbooks/books")
public class BookController {
	
	@Autowired
	private BookBo bookBo;
		
	
	Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		
		ResponseEntity<Book> reBook;
		Book book1 =bookBo.createBook(book);
		reBook = new ResponseEntity<>(book1,HttpStatus.OK);
		return reBook;
	}
	
	@PutMapping("/updateBook")
	public ResponseEntity<Book> updateBook(@RequestBody Book book){
		ResponseEntity<Book> reBook;
		try {
			Book book1= bookBo.updateBook(book);
			reBook = new ResponseEntity<Book>(book1,HttpStatus.OK);
		} catch (DigitalBooksException e) {
			logger.error("Unable to update book"+e);
			reBook = new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		}
		return reBook;
	}
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> fetchAllBooks(){
		ResponseEntity<List<Book>> reBook;
		List<Book> allBook = bookBo.getAllBook();
		reBook= new ResponseEntity<List<Book>>(allBook,HttpStatus.OK);
		return reBook;
	}
	
	@GetMapping("/findBookByName/{name}")
	public ResponseEntity<List<Book>> findByBookName(@PathVariable String name){
		ResponseEntity<List<Book>> reBook;
		List<Book> allBook = bookBo.findByBookName(name);
		reBook = new ResponseEntity<List<Book>>(allBook,HttpStatus.OK);
		return reBook;
	}
	
	@DeleteMapping("deleteBook/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id){
		ResponseEntity<String> status;
		try {
			Book book= bookBo.findById(id);
			book.setStatus("DISABLED");
			bookBo.updateBook(book);
			status = new ResponseEntity<String>("Book deleted successfully",HttpStatus.OK);
		} catch (DigitalBooksException e) {
			logger.error("Unable to delete book"+e);
			status = new ResponseEntity<String>(HttpStatus.BAD_REQUEST); 
		}
		return status;
	}
	

}
