/***********************************************************************
 * Module:  DrinkRating.java
 * Author:  Dusan hehe
 * Purpose: Defines the Class DrinkRating
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class DrinkRating {
	@Id
	@GeneratedValue
	@Column(name = "dnkr_id", nullable = false)
	   public long idDrinkRating;

	   @Column(name = "dnkr_rate", nullable = false)
	   public int rating;
	   
	   @Column(name = "dnkr_dnk", nullable = false)
	   public Drink drink;
	   
	   @Column(name = "dnkr_rst", nullable = false)
	   public Restaurant restauran;
	   
	   @Column(name = "dnkr_num", nullable = false)
	   public int numberOfRates;
	   
	   
   
   
   public long getIdDrinkRating() {
		return idDrinkRating;
	}




	public void setIdDrinkRating(long idDrinkRating) {
		this.idDrinkRating = idDrinkRating;
	}




	public int getRating() {
		return rating;
	}




	public void setRating(int rating) {
		this.rating = rating;
	}




	public Drink getDrink() {
		return drink;
	}




	public void setDrink(Drink drink) {
		this.drink = drink;
	}




	public Restaurant getRestauran() {
		return restauran;
	}




	public void setRestauran(Restaurant restauran) {
		this.restauran = restauran;
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