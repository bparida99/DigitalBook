package com.cts.ns.bo;

import java.util.List;

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
public class NotificationBo {

	private static final String TOPIC = "kafka-topic";

	private static final String TOPIC2 = "purchase-topic";
	
	Logger logger = LoggerFactory.getLogger(NotificationBo.class);
	
	@Autowired
	private NotificationDAO dao;
	
	@KafkaListener(topics = TOPIC, groupId = "group_new", containerFactory = "userKafkaListenerFactory")
	public void consumeJson(String userId) throws JsonMappingException, JsonProcessingException {
		try {
		logger.info("Id recieved from producer :"+userId);
		long id = Long.parseLong(userId);
		Notification n = new Notification();
		n.setUserId(id);
		n.setMsg("Book is disabled by Author");
        dao.saveAndFlush(n);
		}catch(Exception e) {
			logger.error("Error in consumer :"+e);
		}
	}

	@KafkaListener(topics = TOPIC2, groupId = "group_new", containerFactory = "userKafkaListenerFactory")
	public void purchaseNotification(String data) throws JsonMappingException, JsonProcessingException {
		try {
			logger.info("purchaseNotification from producer :"+data);
		}catch(Exception e) {
			logger.error("Error in consumer :"+e);
		}
	}
	
	public List<Notification> getAllByuId(Long id){
		return dao.findByUserId(id);
	}
}
