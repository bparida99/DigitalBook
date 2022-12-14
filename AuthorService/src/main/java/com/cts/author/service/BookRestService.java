package com.cts.author.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cts.author.dto.Book;

@Component
public class BookRestService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String addBook(Book book) {
		HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Book> requestEntity = new HttpEntity<>(book, requestHeaders);
        ResponseEntity<Book> responseEntity= restTemplate.exchange("http://localhost:8084/api/v1/digitalbooks/books/addBook", 
				  org.springframework.http.HttpMethod.POST, requestEntity, Book.class);
        if(responseEntity.getStatusCode() == HttpStatus.OK){
        	return "success";
         }else {
        	 return "error";
         }
		
	}
	

}
