/***********************************************************************
 * Module:  GroceryOffer.java
 * Author:  Bojan
 * Purpose: Defines the Class GroceryOffer
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class GroceryOffer {
	@Id
	@GeneratedValue
	@Column(name="grocery_offer_id", nullable = false)
	public long idGroceryOffer;

	@Column(name="grocery_offer_price", nullable = false)
	public float price;
   
	@ManyToOne
	@JoinColumn(name="offer_id")	
	@JsonIgnore
	public Offer offer;
	
	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	@ManyToOne
	@JoinColumn(name="grocery_id")
	public Grocery grocery;
	
	public GroceryOffer(){}

	public long getIdGroceryOffer() {
		return idGroceryOffer;
	}

	public void setIdGroceryOffer(long idGroceryOffer) {
		this.idGroceryOffer = idGroceryOffer;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Grocery getGrocery() {
		return grocery;
	}

	public void setGrocery(Grocery grocery) {
		this.grocery = grocery;
	}
	
	/*
   public Offer getOffer() {
      return offer;
   }
   
   public void setOffer(Offer newOffer) {
      if (this.offer == null || !this.offer.equals(newOffer))
      {
         if (this.offer != null)
         {
            Offer oldOffer = this.offer;
            this.offer = null;
            oldOffer.removeGroceryOffers(this);
         }
         if (newOffer != null)
         {
            this.offer = newOffer;
            this.offer.addGroceryOffers(this);
         }
      }
   }
	*/
}