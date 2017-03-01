/***********************************************************************
 * Module:  WaiterRating.java
 * Author:  Dusan hehe
 * Purpose: Defines the Class WaiterRating
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class WaiterRating {
	@Id
	@GeneratedValue
	@Column(name = "wtrr_id", nullable = false)
	   public long idWaiterRating;

	   @Column(name = "wtrr_rate", nullable = false)
	   public int rating;
	   
	   @Column(name = "wtrr_wtr", nullable = false)
	   public Waiter waiter;
	   
	   @Column(name = "wtrr_num", nullable = false)
	   public int numberOfRates;
	   
	   




	public long getIdWaiterRating() {
		return idWaiterRating;
	}




	public void setIdWaiterRating(long idWaiterRating) {
		this.idWaiterRating = idWaiterRating;
	}




	public int getRating() {
		return rating;
	}




	public void setRating(int rating) {
		this.rating = rating;
	}



	public Waiter getWaiter() {
		return waiter;
	}




	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
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