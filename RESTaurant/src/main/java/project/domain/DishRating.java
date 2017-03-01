/***********************************************************************
 * Module:  DishRating.java
 * Author:  Dusan hehe
 * Purpose: Defines the Class DishRating
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class DishRating {
	@Id
	@GeneratedValue
   @Column(name = "dshr_id", nullable = false)
   public long idDishRating;

   @Column(name = "dshr_rate", nullable = false)
   public int rating;
   
   @Column(name = "dshr_dsh", nullable = false)
   public Dish dish;
   
   @Column(name = "dshr_rst", nullable = false)
   public Restaurant restauran;
   
   @Column(name = "dshr_num", nullable = false)
   public int numberOfRates;

public long getIdDishRating() {
	return idDishRating;
}

public void setIdDishRating(long idDishRating) {
	this.idDishRating = idDishRating;
}

public int getRating() {
	return rating;
}

public void setRating(int rating) {
	this.rating = rating;
}

public Dish getDish() {
	return dish;
}

public void setDish(Dish dish) {
	this.dish = dish;
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