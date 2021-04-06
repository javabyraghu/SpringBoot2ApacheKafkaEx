package in.nareshit.raghu.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import in.nareshit.raghu.dao.MessageStore;
import in.nareshit.raghu.model.KafkaMessage;

@Component
public class ConsumerService {

	private static final Logger LOG = LoggerFactory.getLogger(ConsumerService.class);
	
	@Autowired
	private MessageStore ms;
	
	@KafkaListener(topics = "${my.kafka-tpc-name}",groupId = "group-id")
	public void readDataFromkafka(String message) {
		LOG.info("MESSAGE RECEIVED AT CONSUMER");
		ms.addMessage(
				new KafkaMessage(message, new Date())
				);
		LOG.info("MESSAGE IS PERSISTED BY STORE");
		
	}
	
}
