package project.domain.dto;

import project.domain.Request;

public class RequestDTO {

	private long requestID;
	
	private long senderID;
	
	private long receiverID;
	
	private String status;
	
	private String senderMail;
	
	private String senderName;
	
	private String senderSurname;
	
	private String receiverMail;
	
	private String receiverName;
	
	private String receiverSurname;
	
	public RequestDTO() {}

	public RequestDTO(Request req) {
		this.requestID = req.getId();
		this.status = req.getStatus();
		this.senderID = req.getSender().getUserID();
		this.senderMail = req.getSender().getEmail();
		this.senderName = req.getSender().getName();
		this.senderSurname = req.getSender().getSurname();
		this.receiverID = req.getReceiver().getUserID();
		this.receiverMail = req.getReceiver().getEmail();
		this.receiverName = req.getReceiver().getName();
		this.receiverSurname = req.getReceiver().getSurname();
	}
	
	public long getRequestID() {
		return requestID;
	}

	public void setRequestID(long requestID) {
		this.requestID = requestID;
	}

	public long getSenderID() {
		return senderID;
	}

	public void setSenderID(long senderID) {
		this.senderID = senderID;
	}

	public long getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(long receiverID) {
		this.receiverID = receiverID;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSenderMail() {
		return senderMail;
	}

	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderSurname() {
		return senderSurname;
	}

	public void setSenderSurname(String senderSurname) {
		this.senderSurname = senderSurname;
	}

	public String getReceiverMail() {
		return receiverMail;
	}

	public void setReceiverMail(String receiverMail) {
		this.receiverMail = receiverMail;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverSurname() {
		return receiverSurname;
	}

	public void setReceiverSurname(String receiverSurname) {
		this.receiverSurname = receiverSurname;
	}

	
	
	
	
}
