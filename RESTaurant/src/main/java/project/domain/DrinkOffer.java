/***********************************************************************
 * Module:  DrinkOffer.java
 * Author:  Bojan
 * Purpose: Defines the Class DrinkOffer
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
public class DrinkOffer {
	@Id
	@GeneratedValue
	@Column(name="drink_offer_id", nullable = false)
	public long idDrinkOffer;
   
	@Column(name="drink_offer_price", nullable = false)
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
	@JoinColumn(name="drink_id")
	public Drink drink;
   
	public DrinkOffer(){}

	public long getIdDrinkOffer() {
		return idDrinkOffer;
	}

	public void setIdDrinkOffer(long idDrinkOffer) {
		this.idDrinkOffer = idDrinkOffer;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
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
            oldOffer.removeDrinkOffers(this);
         }
         if (newOffer != null)
         {
            this.offer = newOffer;
            this.offer.addDrinkOffers(this);
         }
      }
   }
	*/
}