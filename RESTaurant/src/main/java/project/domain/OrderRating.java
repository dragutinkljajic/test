package project.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OrderRating {
	
		@Id
		@GeneratedValue
	   @Column(name = "ordr_id", nullable = false)
	   public long idServiceRaing;
	   
	   @Column(name = "ordr_rate", nullable = false)
	   public int rating;
	   
	   
	   @Column(name = "ordr_rsto", nullable = false)
	   public RestOrder restOrder;


	public RestOrder getRestOrder() {
		return restOrder;
	}

	public void setRestOrder(RestOrder restOrder) {
		this.restOrder = restOrder;
	}

	public long getIdServiceRaing() {
		return idServiceRaing;
	}

	public void setIdServiceRaing(long idServiceRaing) {
		this.idServiceRaing = idServiceRaing;
	}


	  

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
		Restaurant restaurant = restOrder.getReservation().getRestaurant();
		for (int i=0; i<restOrder.getDishOrders().size(); i++){
			restOrder.getDishOrders().get(i).getDish().addRating(rating, restaurant);
		}
		
		for (int i=0; i<restOrder.getDrinkOrders().size(); i++){
			restOrder.getDrinkOrders().get(i).getDrink().addRating(rating, restaurant);
		}
	}

}
