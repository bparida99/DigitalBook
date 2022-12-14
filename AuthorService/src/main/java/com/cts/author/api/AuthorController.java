package com.cts.author.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.author.bo.AuthorBo;
import com.cts.author.config.jwt.AuthRequest;
import com.cts.author.config.jwt.JwtUtil;
import com.cts.author.dto.Book;
import com.cts.author.entity.Author;
import com.cts.author.exception.DigitalBooksException;
import com.cts.author.service.BookRestService;

@RestController
@RequestMapping("/api/v1/digitalbooks/author")
public class AuthorController {

	@Autowired
	private AuthorBo authorBo;
	
	@Autowired
	private BookRestService bookservice;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
    private AuthenticationManager authenticationManager;

	
	Logger logger = LoggerFactory.getLogger(AuthorController.class);
	
	
	@GetMapping("/")
	public String checking() {
		return "Working";
	}
	
	@PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
	
	@PostMapping("/registration")
	public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
		ResponseEntity<Author> rAuthor1;
		Author author1 = authorBo.createAuthor(author);
		rAuthor1 = new ResponseEntity<>(author1,HttpStatus.OK);
		return rAuthor1;
	}
	
	@PutMapping("/updateDetails")
	public ResponseEntity<Author> updateAuthor(@RequestBody Author author){
		ResponseEntity<Author> rAuthor1;
		try {
		Author author1= authorBo.updateAuthor(author);
		rAuthor1 = new ResponseEntity<>(author1,HttpStatus.OK);
		}catch(DigitalBooksException e) {
			logger.error("Failed to update author details: "+e);
			rAuthor1= new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return rAuthor1;
	}
	
	@PostMapping("addBook/{authorId}")
	public ResponseEntity<Object> addBook(@RequestBody Book book,@PathVariable long authorId) throws DigitalBooksException{
		Author author = authorBo.getAuthorById(authorId);
		book.setAuthorId(author.getAuthorId());
		book.setAuthorName(author.getAuthorName());
		String response = bookservice.addBook(book);
		if(response=="success") {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("findByAuthorEmail/{email}")
	public ResponseEntity<Author> findByAuthorEmail(@PathVariable String email) throws DigitalBooksException{
		Author author = authorBo.findByAuthorEmail(email);
		return new ResponseEntity<Author>(author,HttpStatus.OK);
	}
	
	
	
}
