package project.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import project.domain.dto.OfferAcceptedDTO;
import project.domain.dto.RequestDTO;

@Component
public class OfferMessenger {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	public void sendOfferAcceptedTo(OfferAcceptedDTO dto) {
		String topic = "requests?userID=" + dto.getReceiverID();
		this.template.convertAndSend("/topic/" + topic, dto);
	}
	
}
