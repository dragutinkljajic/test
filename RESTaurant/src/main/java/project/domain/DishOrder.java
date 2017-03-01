package project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DishOrder {

	@Id
	@GeneratedValue
	@Column(name = "odr_id", nullable = false)
    private Long id;

	@Column(name = "odr_status", nullable = false)
    private String status;

	@ManyToOne(optional = true)
	@JoinColumn(name="odr_chef",  referencedColumnName="usr_id")
    private Chef chef;

	@ManyToOne(optional = false)
	@JoinColumn(name="odr_dish",  referencedColumnName="dish_id")
    private Dish dish;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="odr_order",  referencedColumnName="ord_id")
	private RestOrder order;
	
	public DishOrder() {}

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

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	public void setRatings(int rating){
		
	}

	public RestOrder getOrder() {
		return order;
	}

	public void setOrder(RestOrder order) {
		this.order = order;
	}
	
	
   
}