package project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Waiter extends Employee {

	private static final long serialVersionUID = -2908038619748273115L;
	
	@Column(name = "wtr_trial", nullable = false)
    public boolean trial = false;

	public boolean isTrial() {
		return trial;
	}

	public void setTrial(boolean trial) {
		this.trial = trial;
	}
	
	public void addRating(int rating){
		
	}

	
   /*
   public java.util.Collection<Order> orders;

   public java.util.Collection<WaiterRating> ratings;
   public java.util.Collection waiterArea;
   
   
   public java.util.Collection<Order> getOrders() {
      if (orders == null)
         orders = new java.util.ArrayList<Order>();
      return orders;
   }
   
   public java.util.Iterator getIteratorOrders() {
      if (orders == null)
         orders = new java.util.ArrayList<Order>();
      return orders.iterator();
   }
   
   public void setOrders(java.util.Collection<Order> newOrders) {
      removeAllOrders();
      for (java.util.Iterator iter = newOrders.iterator(); iter.hasNext();)
         addOrders((Order)iter.next());
   }
   
   public void addOrders(Order newOrder) {
      if (newOrder == null)
         return;
      if (this.orders == null)
         this.orders = new java.util.ArrayList<Order>();
      if (!this.orders.contains(newOrder))
         this.orders.add(newOrder);
   }

   public void removeOrders(Order oldOrder) {
      if (oldOrder == null)
         return;
      if (this.orders != null)
         if (this.orders.contains(oldOrder))
            this.orders.remove(oldOrder);
   }
   
   public void removeAllOrders() {
      if (orders != null)
         orders.clear();
   }

   public java.util.Collection<WaiterRating> getRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<WaiterRating>();
      return ratings;
   }
   
   public java.util.Iterator getIteratorRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<WaiterRating>();
      return ratings.iterator();
   }

   public void setRatings(java.util.Collection<WaiterRating> newRatings) {
      removeAllRatings();
      for (java.util.Iterator iter = newRatings.iterator(); iter.hasNext();)
         addRatings((WaiterRating)iter.next());
   }
   
   public void addRatings(WaiterRating newWaiterRating) {
      if (newWaiterRating == null)
         return;
      if (this.ratings == null)
         this.ratings = new java.util.ArrayList<WaiterRating>();
      if (!this.ratings.contains(newWaiterRating))
      {
         this.ratings.add(newWaiterRating);
         newWaiterRating.setWaiter(this);      
      }
   }

   public void removeRatings(WaiterRating oldWaiterRating) {
      if (oldWaiterRating == null)
         return;
      if (this.ratings != null)
         if (this.ratings.contains(oldWaiterRating))
         {
            this.ratings.remove(oldWaiterRating);
            oldWaiterRating.setWaiter((Waiter)null);
         }
   }
   
   public void removeAllRatings() {
      if (ratings != null)
      {
         WaiterRating oldWaiterRating;
         for (java.util.Iterator iter = getIteratorRatings(); iter.hasNext();)
         {
            oldWaiterRating = (WaiterRating)iter.next();
            iter.remove();
            oldWaiterRating.setWaiter((Waiter)null);
         }
      }
   }
	*/
}