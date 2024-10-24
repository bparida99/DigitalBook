package com.cts.ns.bo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

//@Service
public class NotificationEventManual implements AcknowledgingMessageListener<Integer,String> {
    Logger logger = LoggerFactory.getLogger(NotificationEventManual.class);

    @Override
    @KafkaListener(topics = "${spring.kafka.topics.first}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(ConsumerRecord<Integer, String> data, Acknowledgment acknowledgment) {
     logger.info("Data received in manual ack : "+data);
     acknowledgment.acknowledge();
    }
}
