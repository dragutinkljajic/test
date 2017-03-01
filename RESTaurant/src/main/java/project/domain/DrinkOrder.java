package project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DrinkOrder {

	@Id
	@GeneratedValue
	@Column(name = "odk_id", nullable = false)
	private Long id;

	@Column(name = "odk_status", nullable = false)
    private String status;

	@ManyToOne(optional = true)
	@JoinColumn(name="odk_bartender",  referencedColumnName="usr_id")
	private Bartender bartender; 
	
	@ManyToOne(optional = false)
	@JoinColumn(name="odk_drink",  referencedColumnName="drink_id")
    private Drink drink;

	@ManyToOne(optional = false)
	@JoinColumn(name="odk_order",  referencedColumnName="ord_id")
	private RestOrder order;
   
	public DrinkOrder() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Bartender getBartender() {
		return bartender;
	}

	public void setBartender(Bartender bartender) {
		this.bartender = bartender;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public RestOrder getOrder() {
		return order;
	}

	public void setOrder(RestOrder order) {
		this.order = order;
	}
	
	public void setRatings(int rating){
		
	}
	
	

}