package in.nareshit.raghu.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.nareshit.raghu.model.KafkaMessage;
import in.nareshit.raghu.repo.KafkaMessageRepository;

@Component
public class MessageStore {
	
	private static final Logger LOG = LoggerFactory.getLogger(MessageStore.class);
	
	@Autowired
	private KafkaMessageRepository repo;
	
	public void addMessage(KafkaMessage msg){
		msg=repo.save(msg);
		LOG.info("MESSAGE IS STORED AT DATABASE {}",msg.getId());
	}
	
	public List<KafkaMessage> getAllMessages() {
		LOG.info("FETCHING MESSAGES FROM DATABASE");
		return repo.findAll();
	}
}
