package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Dish {
	
	@Id
	@GeneratedValue
	@Column(name="dish_id", nullable = false)
	public long idDish;
	
	@Column(name="dish_label", nullable = false)
	public String label;
	
	@Column(name="dish_description")
	public String description;
	
	@Column(name="dish_price", nullable = false)
	public float price;
   
	@OneToMany(mappedBy="dish")
	@JsonIgnore
	private List<DishOrder> dishOrders;
	
	@Column(name="dish_type", nullable = false)
	public DishType type;
	
	//public java.util.Collection<DishRating> ratings;
	

	@ManyToOne
	@JoinColumn(name="menu_id")
	public Menu menu;
	
	public Dish() {}

	public long getIdDish() {
		return idDish;
	}

	public void setIdDish(long idDish) {
		this.idDish = idDish;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<DishOrder> getDishOrders() {
		return dishOrders;
	}

	public void setDishOrders(List<DishOrder> dishOrders) {
		this.dishOrders = dishOrders;
	}
	
	public DishType getType() {
		return type;
	}

	public void setType(DishType type) {
		this.type = type;
	}
	
	public void addRating(int rating, Restaurant restaurant){
	}
	
	
}