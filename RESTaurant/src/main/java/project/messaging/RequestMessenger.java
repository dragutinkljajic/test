package project.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import project.domain.Request;
import project.domain.dto.RequestDTO;

@Component
public class RequestMessenger {

	@Autowired
	private SimpMessagingTemplate template;
	
	public void sendRequestTo(RequestDTO dto) {
		String topic = "requests?userID=" + dto.getReceiverID();
		this.template.convertAndSend("/topic/" + topic, dto);
	}
	
	public void sendUpdateTo(RequestDTO dto) {
		String topic = "requests?userID=" + dto.getSenderID();
		this.template.convertAndSend("/topic/" + topic, dto);
	}
}
