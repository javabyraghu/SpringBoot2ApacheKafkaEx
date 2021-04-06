package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.raghu.model.KafkaMessage;

public interface KafkaMessageRepository 
	extends JpaRepository<KafkaMessage, Integer> {

}
