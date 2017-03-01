/***********************************************************************
 * Module:  ChefRating.java
 * Author:  Bojan
 * Purpose: Defines the Class ChefRating
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** @pdOid 12aea99d-e037-434f-bcc3-a3fabdcb9fb4 */
public class ChefRating {
		@Id
		@GeneratedValue
		@Column(name = "chfr_id", nullable = false)
	   public long idChefRating;

	   @Column(name = "chfr_rate", nullable = false)
	   public int rating;
	   
	   @Column(name = "chfr_wtr", nullable = false)
	   public Chef chef;
	   
	   @Column(name = "chfr_num", nullable = false)
	   public int numberOfRates;
	   
	   






	public long getIdChefRating() {
		return idChefRating;
	}




	public void setIdChefRating(long idChefRating) {
		this.idChefRating = idChefRating;
	}




	public Chef getChef() {
		return chef;
	}




	public void setChef(Chef chef) {
		this.chef = chef;
	}




	public int getRating() {
		return rating;
	}




	public void setRating(int rating) {
		this.rating = rating;
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