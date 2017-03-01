package project.domain.dto;

import java.util.Set;

import project.domain.Drink;
import project.domain.Grocery;
import project.domain.RestaurantManager;

public class BidDTO {
	public Long idBid;
	
	public Long getIdBid() {
		return idBid;
	}

	public void setIdBid(Long idBid) {
		this.idBid = idBid;
	}

	public String beginning;
	
	public String end;
	
	public Set<Drink> drinks;
	
	public Set<Grocery> groceries;
	
	public RestaurantManager manager;
	
	public BidDTO() {}

	public String getBeginning() {
		return beginning;
	}

	public void setBeginning(String beginning) {
		this.beginning = beginning;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Set<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(Set<Drink> drinks) {
		this.drinks = drinks;
	}

	public Set<Grocery> getGroceries() {
		return groceries;
	}

	public void setGroceries(Set<Grocery> groceries) {
		this.groceries = groceries;
	}

	public RestaurantManager getManager() {
		return manager;
	}

	public void setManager(RestaurantManager manager) {
		this.manager = manager;
	}
}
