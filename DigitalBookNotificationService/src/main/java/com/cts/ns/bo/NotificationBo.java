package com.cts.ns.bo;

import java.util.List;

import com.cts.ns.entity.Purchase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.cts.ns.dao.NotificationDAO;
import com.cts.ns.entity.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


@Service
@Slf4j
public class NotificationBo {
	
	@Autowired
	private NotificationDAO dao;

	@Autowired
	private ObjectMapper objectMapper;

	@KafkaListener(topics = "${spring.kafka.topics.second}",
			groupId = "${spring.kafka.consumer.group-id}")
	public void consumeJson(String userId) throws JsonMappingException, JsonProcessingException {
		try {
		log.info("Id recieved from producer :"+userId);
		long id = Long.parseLong(userId);
		Notification n = new Notification();
		n.setUserId(id);
		n.setMsg("Book is disabled by Author");
        dao.saveAndFlush(n);
		}catch(Exception e) {
			log.error("Error in consumer :"+e);
		}
	}

	@KafkaListener(topics = "${spring.kafka.topics.first}",
			groupId = "${spring.kafka.consumer.group-id}")
	public void purchaseNotification(ConsumerRecord<Integer,String> consumerRecord) throws JsonMappingException, JsonProcessingException {
		try {
			log.info("purchaseNotification from producer :"+consumerRecord);
			Purchase purchase=	objectMapper.readValue(consumerRecord.value(), Purchase.class);
			log.info("After deserielization :"+purchase.getBook_name());
		}catch(Exception e) {
			log.error("Error in consumer :"+e);
		}
	}

	public List<Notification> getAllByuId(Long id){
		return dao.findByUserId(id);
	}
}
