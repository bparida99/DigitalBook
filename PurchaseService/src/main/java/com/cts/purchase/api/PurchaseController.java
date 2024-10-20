package com.cts.purchase.api;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.purchase.bo.PurchaseBo;
import com.cts.purchase.entity.Purchase;
import com.cts.purchase.exception.DigitalBooksException;

@RestController
@RequestMapping("/api/v1/digitalbooks/purchase")
@CrossOrigin("*")
@Slf4j
public class PurchaseController {

	Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	@Autowired
	private PurchaseBo booksOfUserBo;
	
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	private static final String TOPIC = "kafka-topic";

	
	@PostMapping("/addPurchase")
	public ResponseEntity<Purchase> addPurchase(@RequestBody Purchase bu) throws JsonProcessingException {
		bu.setExpiryDate(com.cts.purchase.entity.DigitalBooksConstants.defaultDate);
		bu.setPurchseDate(Date.valueOf(LocalDate.now()));
		bu = booksOfUserBo.addPurchase(bu);
		booksOfUserBo.sendPurchaseNotification(bu);
		return new ResponseEntity<Purchase>(bu, HttpStatus.OK);
	}
	
	@GetMapping("/getPurchaseByReaderId/{id}")
	public ResponseEntity<List<Purchase>> getPurchseByUserId(@PathVariable Long id){
		ResponseEntity<List<Purchase>> reBu;
		List<Purchase> buList = booksOfUserBo.findPurchaseByUserId(id);
		reBu = new ResponseEntity<List<Purchase>>(buList,HttpStatus.OK);
		return reBu;
	}
	
	@GetMapping("/getPurchaseByBookId/{id}")
	public ResponseEntity<List<Purchase>> getPurchseByBookId(@PathVariable Long id){
		ResponseEntity<List<Purchase>> reBu;
		List<Purchase> buList = booksOfUserBo.findPurchaseByBookId(id);
		reBu = new ResponseEntity<List<Purchase>>(buList,HttpStatus.OK);
		return reBu;
	}
	
    @PutMapping("/updatePurchase")
    public ResponseEntity<Purchase> updatePurchase(@RequestBody Purchase purchase) throws DigitalBooksException{
    	try {
    	booksOfUserBo.findPurchaseById(purchase.getBooking_id());
    	Purchase purchase2 = booksOfUserBo.addPurchase(purchase);
    	return new ResponseEntity<Purchase>(purchase2,HttpStatus.OK);
    	}catch(DigitalBooksException e) {
    		logger.error("exception"+e);
    		return new ResponseEntity<Purchase>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @PutMapping("/cancelReaderPurchase")
    public ResponseEntity<Purchase> updateReaderPurchase(@RequestBody Purchase purchase) throws DigitalBooksException{
    	try {
    	booksOfUserBo.findPurchaseById(purchase.getBooking_id());
    	purchase.setExpiryDate(Date.valueOf(LocalDate.now()));
    	purchase.setCanceledBy("READER");
    	Purchase purchase2 = booksOfUserBo.addPurchase(purchase);
    	return new ResponseEntity<Purchase>(purchase2,HttpStatus.OK);
    	}catch(DigitalBooksException e) {
    		logger.error("exception"+e);
    		return new ResponseEntity<Purchase>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @GetMapping("/cancelPurchase/{bookId}")
    public ResponseEntity<Object> cancelPurchase(@PathVariable long bookId){
    	List<Purchase> list = booksOfUserBo.findPurchaseByBookId(bookId);
    	list.forEach(item->{
    		item.setCanceledBy("AUTHOR");
    		item.setExpiryDate(Date.valueOf(LocalDate.now()));
    	    booksOfUserBo.updatePurchase(item);
    	    sendNotification(item.getUserId());
    	});
    	return new ResponseEntity<Object>(HttpStatus.OK);
    }
    
    private void sendNotification(long userId) {
    	Integer key = Math.toIntExact(userId);
    	kafkaTemplate.send(TOPIC,key,String.valueOf(userId));
    }


    
}
