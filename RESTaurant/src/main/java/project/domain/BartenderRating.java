/***********************************************************************
 * Module:  BartenderRating.java
 * Author:  Dusan hehe
 * Purpose: Defines the Class BartenderRating
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class BartenderRating {
	@Id
	@GeneratedValue
	@Column(name = "barr_id", nullable = false)
	   public long idBartenderRating;

	   @Column(name = "barr_rate", nullable = false)
	   public int rating;
	   
	   @Column(name = "barr_wtr", nullable = false)
	   public Bartender bartender;
	   
	   @Column(name = "barr_num", nullable = false)
	   public int numberOfRates;
	   
	   

	public long getIdBartenderRating() {
		return idBartenderRating;
	}




	public void setIdBartenderRating(long idBartenderRating) {
		this.idBartenderRating = idBartenderRating;
	}




	public Bartender getBartender() {
		return bartender;
	}




	public void setBartender(Bartender bartender) {
		this.bartender = bartender;
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