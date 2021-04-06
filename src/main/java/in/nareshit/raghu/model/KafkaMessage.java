package in.nareshit.raghu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="kafkak_message_tab")
public class KafkaMessage {

	@Id
	@GeneratedValue
	@Column(name="km_id_col")
	private Integer id;
	
	@Column(name="km_msg_col")
	@NonNull
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="km_dte_stmap_col")
	@NonNull
	private Date dteStamp;
}
