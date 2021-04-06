package in.nareshit.raghu.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nareshit.raghu.dao.MessageStore;
import in.nareshit.raghu.model.KafkaMessage;
import in.nareshit.raghu.service.ProducerService;

@RestController
@RequestMapping("/kafka")
public class KafkaRestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(KafkaRestController.class);

	@Autowired
	private ProducerService producer;
	
	@Autowired
	private MessageStore store;
	
	@GetMapping("/send/{msg}")
	public ResponseEntity<String> sendMessage(
			@PathVariable String msg
			) 
	{
		ResponseEntity<String> resp = null;
		LOG.info("REST CONTROLLER IS ABOUT TO SEND MESSAGE");
		try {
			producer.send(msg);
			LOG.info("REST CONTROLLER FINISHED SENDING");
			resp = new ResponseEntity<String>(
					"SENT SUCCESSFULLY!",
					HttpStatus.CREATED);
			
		} catch (Exception e) {
			LOG.error("SENDING FAILED {}",e.getMessage());
			e.printStackTrace();
			resp = new ResponseEntity<String>(
					"UNABLE TO SEND!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("RETURN FROM REST CONTROLLER  SEND MESSAGE");
		
		return resp;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> viewAllMessages(){
		LOG.info("ENTERED INTO FETCH ALL MESG");
		ResponseEntity<?> resp = null;
		try {
			List<KafkaMessage> list = store.getAllMessages();
			LOG.info("DATA FETCH SUCCESSFUL!");
			resp = new ResponseEntity<List<KafkaMessage>>(list,HttpStatus.OK);
		}  catch (Exception e) {
			LOG.error("FETCHING FAILED {}",e.getMessage());
			e.printStackTrace();
			resp = new ResponseEntity<String>(
					"UNABLE TO FETCH!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return resp;
	}
}
