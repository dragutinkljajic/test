/***********************************************************************
 * Module:  Offer.java
 * Author:  Bojan
 * Purpose: Defines the Class Offer
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Offer {
	@Id
	@GeneratedValue
	@Column(name="offer_id", nullable = false)
	public long idOffer;
	
	@Column(name="offer_status", nullable = false)
	public String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getIdOffer() {
		return idOffer;
	}

	public void setIdOffer(long idOffer) {
		this.idOffer = idOffer;
	}

	public Date getDelivery() {
		return delivery;
	}

	public void setDelivery(Date delivery) {
		this.delivery = delivery;
	}

	public Date getWarranty() {
		return warranty;
	}

	public void setWarranty(Date warranty) {
		this.warranty = warranty;
	}

	public Date getLastsUntil() {
		return lastsUntil;
	}

	public void setLastsUntil(Date lastsUntil) {
		this.lastsUntil = lastsUntil;
	}

	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public Set<DrinkOffer> getDrinkOffers() {
		return drinkOffers;
	}

	public void setDrinkOffers(Set<DrinkOffer> drinkOffers) {
		this.drinkOffers = drinkOffers;
	}

	public Set<GroceryOffer> getGroceryOffers() {
		return groceryOffers;
	}

	public void setGroceryOffers(Set<GroceryOffer> groceryOffers) {
		this.groceryOffers = groceryOffers;
	}

	@Column(name="offer_delivery", nullable = false)
	public Date delivery;

	@Column(name="offer_warranty", nullable = false)
	public Date warranty;

	@Column(name="offer_lasts_until", nullable = false)
	public Date lastsUntil;
	
	@ManyToOne
	@JoinColumn(name="bid_id")
	public Bid bid;
	
	@ManyToOne
	@JoinColumn(name="usr_id")
	public Supplier supplier;
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@OneToMany(mappedBy="offer")
	public Set<DrinkOffer> drinkOffers;

	@OneToMany(mappedBy="offer")
	public Set<GroceryOffer> groceryOffers;
	
	public Offer(){}
   /*
   public java.util.Collection<Bid> getBids() {
      if (bids == null)
         bids = new java.util.ArrayList<Bid>();
      return bids;
   }
   
   public java.util.Iterator getIteratorBids() {
      if (bids == null)
         bids = new java.util.ArrayList<Bid>();
      return bids.iterator();
   }
   
   public void setBids(java.util.Collection<Bid> newBids) {
      removeAllBids();
      for (java.util.Iterator iter = newBids.iterator(); iter.hasNext();)
         addBids((Bid)iter.next());
   }
   
   public void addBids(Bid newBid) {
      if (newBid == null)
         return;
      if (this.bids == null)
         this.bids = new java.util.ArrayList<Bid>();
      if (!this.bids.contains(newBid))
      {
         this.bids.add(newBid);
         newBid.addOffers(this);      
      }
   }
   
   public void removeBids(Bid oldBid) {
      if (oldBid == null)
         return;
      if (this.bids != null)
         if (this.bids.contains(oldBid))
         {
            this.bids.remove(oldBid);
            oldBid.removeOffers(this);
         }
   }
   
   public void removeAllBids() {
      if (bids != null)
      {
         Bid oldBid;
         for (java.util.Iterator iter = getIteratorBids(); iter.hasNext();)
         {
            oldBid = (Bid)iter.next();
            iter.remove();
            oldBid.removeOffers(this);
         }
      }
   }
   public java.util.Collection<DrinkOffer> getDrinkOffers() {
      if (drinkOffers == null)
         drinkOffers = new java.util.ArrayList<DrinkOffer>();
      return drinkOffers;
   }
   
   public java.util.Iterator getIteratorDrinkOffers() {
      if (drinkOffers == null)
         drinkOffers = new java.util.ArrayList<DrinkOffer>();
      return drinkOffers.iterator();
   }
   
   public void setDrinkOffers(java.util.Collection<DrinkOffer> newDrinkOffers) {
      removeAllDrinkOffers();
      for (java.util.Iterator iter = newDrinkOffers.iterator(); iter.hasNext();)
         addDrinkOffers((DrinkOffer)iter.next());
   }
   
   public void addDrinkOffers(DrinkOffer newDrinkOffer) {
      if (newDrinkOffer == null)
         return;
      if (this.drinkOffers == null)
         this.drinkOffers = new java.util.ArrayList<DrinkOffer>();
      if (!this.drinkOffers.contains(newDrinkOffer))
      {
         this.drinkOffers.add(newDrinkOffer);
         newDrinkOffer.setOffer(this);      
      }
   }
   
   public void removeDrinkOffers(DrinkOffer oldDrinkOffer) {
      if (oldDrinkOffer == null)
         return;
      if (this.drinkOffers != null)
         if (this.drinkOffers.contains(oldDrinkOffer))
         {
            this.drinkOffers.remove(oldDrinkOffer);
            oldDrinkOffer.setOffer((Offer)null);
         }
   }
   
   public void removeAllDrinkOffers() {
      if (drinkOffers != null)
      {
         DrinkOffer oldDrinkOffer;
         for (java.util.Iterator iter = getIteratorDrinkOffers(); iter.hasNext();)
         {
            oldDrinkOffer = (DrinkOffer)iter.next();
            iter.remove();
            oldDrinkOffer.setOffer((Offer)null);
         }
      }
   }
   public java.util.Collection<GroceryOffer> getGroceryOffers() {
      if (groceryOffers == null)
         groceryOffers = new java.util.ArrayList<GroceryOffer>();
      return groceryOffers;
   }
   
   public java.util.Iterator getIteratorGroceryOffers() {
      if (groceryOffers == null)
         groceryOffers = new java.util.ArrayList<GroceryOffer>();
      return groceryOffers.iterator();
   }
   
   public void setGroceryOffers(java.util.Collection<GroceryOffer> newGroceryOffers) {
      removeAllGroceryOffers();
      for (java.util.Iterator iter = newGroceryOffers.iterator(); iter.hasNext();)
         addGroceryOffers((GroceryOffer)iter.next());
   }
   
   public void addGroceryOffers(GroceryOffer newGroceryOffer) {
      if (newGroceryOffer == null)
         return;
      if (this.groceryOffers == null)
         this.groceryOffers = new java.util.ArrayList<GroceryOffer>();
      if (!this.groceryOffers.contains(newGroceryOffer))
      {
         this.groceryOffers.add(newGroceryOffer);
         newGroceryOffer.setOffer(this);      
      }
   }
   
   public void removeGroceryOffers(GroceryOffer oldGroceryOffer) {
      if (oldGroceryOffer == null)
         return;
      if (this.groceryOffers != null)
         if (this.groceryOffers.contains(oldGroceryOffer))
         {
            this.groceryOffers.remove(oldGroceryOffer);
            oldGroceryOffer.setOffer((Offer)null);
         }
   }
   
   public void removeAllGroceryOffers() {
      if (groceryOffers != null)
      {
         GroceryOffer oldGroceryOffer;
         for (java.util.Iterator iter = getIteratorGroceryOffers(); iter.hasNext();)
         {
            oldGroceryOffer = (GroceryOffer)iter.next();
            iter.remove();
            oldGroceryOffer.setOffer((Offer)null);
         }
      }
   }
	*/
}