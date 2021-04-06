package in.nareshit.raghu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProducerService.class);

	@Value("${my.kafka-tpc-name}")
	private String topic;
	
	@Autowired
	private KafkaTemplate<String, String> template;
	
	public void send(String message) {
		LOG.info("SENDING MESSAGE TO KAFKA RUNTIME");
		template.send(topic, message);
		LOG.info("SENDT SUCCESFULLY FROM PRODUCER!!");
	}
	
}
