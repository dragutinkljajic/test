/***********************************************************************
 * Module:  RestaurantRating.java
 * Author:  Dusan hehe
 * Purpose: Defines the Class RestaurantRating
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class RestaurantRating {
	@Id
	@GeneratedValue
	@Column(name = "rstr_id", nullable = false)
	   public long idRestaurantRating;

	   @Column(name = "rstr_rate", nullable = false)
	   public int rating;
	   
	   @Column(name = "rstr_rst", nullable = false)
	   public Restaurant restaurant;
	   
	   @Column(name = "rstr_num", nullable = false)
	   public int numberOfRates;
	   
	   
   
   
   public long getIdRestaurantRating() {
		return idRestaurantRating;
	}




	public void setIdRestaurantRating(long idRestaurantRating) {
		this.idRestaurantRating = idRestaurantRating;
	}




	public int getRating() {
		return rating;
	}




	public void setRating(int rating) {
		this.rating = rating;
	}




	public Restaurant getRestaurant() {
		return restaurant;
	}




	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}




	public int getNumberOfRates() {
		return numberOfRates;
	}




	public void setNumberOfRates(int numberOfRates) {
		this.numberOfRates = numberOfRates;
	}




public void addRating(int rating) {
		int rates = this.numberOfRates;
		int compute = rates * this.rating;
		this.numberOfRates = ++rates;
		compute = compute + rating;
		int newRating = compute / this.numberOfRates;
		this.rating = newRating;
		
	}
	

}