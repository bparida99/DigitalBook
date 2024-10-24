package com.cts.ns.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;
import org.springframework.util.backoff.ExponentialBackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.net.SocketException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {
    Logger logger = LoggerFactory.getLogger(KafkaConsumerConfiguration.class);

    @Value("${spring.kafka.topics.RETRY}")
    private static String RETRY;
    @Value("${spring.kafka.topics.DLT}")
    private static String DLT;

    @Autowired
    private KafkaTemplate kafkaTemplate;


    public DeadLetterPublishingRecoverer deadLetterPublishingRecoverer() {
        var deadLetterPublishingRecoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
                (r, e) -> {
                    if (e instanceof RuntimeException) {
                        return new TopicPartition(RETRY, r.partition());
                    } else {
                        return new TopicPartition(DLT, r.partition());
                    }

                });

        return deadLetterPublishingRecoverer;
    }

    public DefaultErrorHandler defaultErrorHandler() {
        var fixedBackOff = new FixedBackOff(1000l, 2);
        //OR
        var expoBackup = new ExponentialBackOffWithMaxRetries(2);
        expoBackup.setInitialInterval(1000l);
        expoBackup.setMultiplier(2l);
        expoBackup.setMaxInterval(2000l);
        var errorHandler = new DefaultErrorHandler(
                deadLetterPublishingRecoverer(),
                expoBackup);
        errorHandler.addRetryableExceptions(SocketException.class);
        errorHandler.setRetryListeners((record, ex, deliveryAttempt) -> {
            logger.error("Error in fetching record {}" + " Attempt :" + deliveryAttempt + " Exception: " + ex);
        });
        return errorHandler;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, String>
    userKafkaListenerFactory(ConsumerFactory<Integer, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setCommonErrorHandler(defaultErrorHandler());
        //factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
}