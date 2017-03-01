package project.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RestaurantManager extends User {

	//private static final long serialVersionUID = -5315143688588885031L;

	@Column(name = "rsm_name", nullable = false)
	private String name;
	
	@Column(name = "rsm_surname", nullable = false)
    private String surname;
	
	@ManyToOne
	@JoinColumn(name="rst_id")
	private Restaurant restaurant;
	
	@OneToMany(mappedBy="manager")
	@JsonIgnore
	public Set<Bid> bids;
	
	public RestaurantManager() {}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
   /*
   public Restaurant restoraunt;

   public java.util.Collection<Bid> bids;

   public java.util.Collection<Supplier> managers;
   
   public Restaurant getRestoraunt() {
      return restoraunt;
   }
   
   public void setRestoraunt(Restaurant newRestaurant) {
      if (this.restoraunt == null || !this.restoraunt.equals(newRestaurant))
      {
         if (this.restoraunt != null)
         {
            Restaurant oldRestaurant = this.restoraunt;
            this.restoraunt = null;
            oldRestaurant.removeManagers(this);
         }
         if (newRestaurant != null)
         {
            this.restoraunt = newRestaurant;
            this.restoraunt.addManagers(this);
         }
      }
   }

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
         newBid.setManager(this);      
      }
   }
   
   public void removeBids(Bid oldBid) {
      if (oldBid == null)
         return;
      if (this.bids != null)
         if (this.bids.contains(oldBid))
         {
            this.bids.remove(oldBid);
            oldBid.setManager((RestorauntManager)null);
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
            oldBid.setManager((RestorauntManager)null);
         }
      }
   }

   public java.util.Collection<Supplier> getManagers() {
      if (managers == null)
         managers = new java.util.ArrayList<Supplier>();
      return managers;
   }

   public java.util.Iterator getIteratorManagers() {
      if (managers == null)
         managers = new java.util.ArrayList<Supplier>();
      return managers.iterator();
   }
   
   public void setManagers(java.util.Collection<Supplier> newManagers) {
      removeAllManagers();
      for (java.util.Iterator iter = newManagers.iterator(); iter.hasNext();)
         addManagers((Supplier)iter.next());
   }

   public void addManagers(Supplier newSupplier) {
      if (newSupplier == null)
         return;
      if (this.managers == null)
         this.managers = new java.util.ArrayList<Supplier>();
      if (!this.managers.contains(newSupplier))
      {
         this.managers.add(newSupplier);
         newSupplier.addSuppliers(this);      
      }
   }
   
   public void removeManagers(Supplier oldSupplier) {
      if (oldSupplier == null)
         return;
      if (this.managers != null)
         if (this.managers.contains(oldSupplier))
         {
            this.managers.remove(oldSupplier);
            oldSupplier.removeSuppliers(this);
         }
   }
   
   public void removeAllManagers() {
      if (managers != null)
      {
         Supplier oldSupplier;
         for (java.util.Iterator iter = getIteratorManagers(); iter.hasNext();)
         {
            oldSupplier = (Supplier)iter.next();
            iter.remove();
            oldSupplier.removeSuppliers(this);
         }
      }
   }
	*/
}