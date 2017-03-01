package project.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer extends User {

	private static final long serialVersionUID = -2780627537315030281L;

	@Column(name = "cst_name", nullable = true)
	private String name;
	
	@Column(name = "cst_surname", nullable = true)
    private String surname;
	
	@Column(name = "cst_address", nullable = true)
    private String address;
	
	@Column(name = "cst_date_birth", nullable = true)
    private Date dateBirth;
	
	public Customer() {}

	@OneToMany(mappedBy="customer")
	@JsonIgnore
    private List<Reservation> reservations;

    /*
    public java.util.Collection<Invite> invites;

    public VisitHistory history;

    public java.util.Collection<Order> orders;
	*/
	
	@OneToMany(mappedBy="receiver")
	@JsonIgnore
    public List<Request> incomingRequests;

	@OneToMany(mappedBy="sender")
	@JsonIgnore
    public List<Request> outcomingRequests;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="Friends",
	 joinColumns=@JoinColumn(name="usr_id"),
	 inverseJoinColumns=@JoinColumn(name="frd_id"))
	@JsonIgnore
    private List<Customer> friends;
    
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="Friends",
	 joinColumns=@JoinColumn(name="frd_id"),
	 inverseJoinColumns=@JoinColumn(name="usr_id"))
	@JsonIgnore
    private List<Customer> friendOf;
    
    public String getName() {
    	return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public List<Customer> getFriends() {
		return friends;
	}

	public void setFriends(List<Customer> friends) {
		this.friends = friends;
	}

	public List<Customer> getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(List<Customer> friendOf) {
		this.friendOf = friendOf;
	}

	public List<Request> getIncomingRequests() {
		return incomingRequests;
	}

	public void setIncomingRequests(List<Request> incomingRequests) {
		this.incomingRequests = incomingRequests;
	}

	public List<Request> getOutcomingRequests() {
		return outcomingRequests;
	}

	public void setOutcomingRequests(List<Request> outcomingRequests) {
		this.outcomingRequests = outcomingRequests;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	
	

}