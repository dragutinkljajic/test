package project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Request {

	@Id
	@GeneratedValue
	@Column(name = "req_id", nullable = false)
	private long id;

	@Column(name = "req_status", nullable = false)
    private String status;

	@ManyToOne(optional=false)
	@JoinColumn(name="req_receiver",  referencedColumnName="usr_id")
    private Customer receiver;

	@ManyToOne(optional=false)
	@JoinColumn(name="req_sender",  referencedColumnName="usr_id")
    private Customer sender;
    
    public Request() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public Customer getReceiver() {
		return receiver;
	}

	public void setReceiver(Customer receiver) {
		this.receiver = receiver;
	}

	public Customer getSender() {
		return sender;
	}

	public void setSender(Customer sender) {
		this.sender = sender;
	}
    
    
}