package com.cts.purchase.bo.impl;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cts.purchase.bo.PurchaseBo;
import com.cts.purchase.dao.PurchaseDao;
import com.cts.purchase.entity.Purchase;
import com.cts.purchase.exception.DigitalBooksException;

@Service
@Slf4j
public class BooksOfUserBoImpl implements PurchaseBo{
	private PurchaseDao dao;

	private ObjectMapper objectMapper;

	private KafkaTemplate<Integer, String> kafkaTemplate;


	@Value("${spring.kafka.topic}")
	private String topic;

	@Autowired
	public BooksOfUserBoImpl(ObjectMapper objectMapper,
							 KafkaTemplate<Integer, String> kafkaTemplate,PurchaseDao dao) {
		this.objectMapper = objectMapper;
		this.kafkaTemplate = kafkaTemplate;
		this.dao=dao;
	}

	@Override
	public Purchase addPurchase(Purchase booksofUser) {
		return dao.saveAndFlush(booksofUser);
	}

	@Override
	public Purchase updatePurchase(Purchase purchase) {
		findPurchaseByBookId(purchase.getBooking_id());
		return dao.saveAndFlush(purchase);
	}

	@Override
	public List<Purchase> findPurchaseByBookId(Long id) {
		return dao.findByBookId(id);
	}

	@Override
	public List<Purchase> findPurchaseByUserId(Long id) {
		return dao.findByUserId(id);
	}

	@Override
	public Purchase findPurchaseById(Long id) throws DigitalBooksException {
		Purchase bu= dao.findById(id).get();
		if(bu==null) {
			throw new DigitalBooksException("No purcase found with id:"+id);
		}
		return bu;
	}

	@Override
	public void sendPurchaseNotification(Purchase purchase) throws JsonProcessingException {
		var key = Math.toIntExact(purchase.getUserId()) ;
		var value = objectMapper.writeValueAsString(purchase);
		kafkaTemplate.send(topic,key,value).
				whenComplete((integerStringSendResult, throwable)-> {
			 if(throwable!=null){
				 log.error("Key :"+key+"  Value :"+ value+"  Error: "+throwable.getMessage());
			 }else{
				 log.info(" Message sent successfully : "+"Key :"+key+"  Value :"+ value);
			 }
		});
	}

}
