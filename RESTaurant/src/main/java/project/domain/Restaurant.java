/***********************************************************************
 * Module:  Restaurant.java
 * Author:  Bojan
 * Purpose: Defines the Class Restaurant
 ***********************************************************************/
package project.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Restaurant implements Serializable{

	//private static final long serialVersionUID = 4684933772758103682L;

	@Id
	@GeneratedValue
	@Column(name="rst_id", nullable = false)
	public long restaurantID;
	   
	@Column(name="rst_name", nullable = false)
	public String name;
	   
	@Column(name="rst_type", nullable = true)
	public String type;
	   
	@Column(name="rst_description", nullable = true)
	public String description;
	
	@OneToMany(mappedBy="restaurant")
	@JsonIgnore
	public Set<RestaurantManager> restaurantManagers;
	
	@OneToMany(mappedBy="restaurant")
	@JsonIgnore
	public Set<Employee> employees;
	
	@OneToOne(mappedBy="restaurant")
	public Menu menu;
	
	@OneToOne(mappedBy="restaurant")
	public SeatingArrangement seatingArrangement;
	
	@OneToOne(mappedBy="restaurant")
	public WorkSchedule schedule;
	
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@OneToOne(mappedBy="restaurant")
	public DrinksMenu drinksMenu;
	
	@OneToMany(mappedBy="restaurant")
	@JsonIgnore
	private List<Reservation> reservations;
	
	public Restaurant(){}

	public WorkSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(WorkSchedule schedule) {
		this.schedule = schedule;
	}

	public long getRestaurantID() {
		return restaurantID;
	}
	
	public void setRestaurantID(long restaurantID) {
		this.restaurantID = restaurantID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public void addRating(int rating){
	}
	
	
	//public Set<RestaurantManager> getRestaurantManagers() {
	//	return restaurantManagers;
	//}

	//public void setRestaurantManagers(Set<RestaurantManager> restaurantManagers) {
	//	this.restaurantManagers = restaurantManagers;
	//}
	
   /*
   public SeatingArrangement seating;
   
   public java.util.Collection<Employee> employees;
   
   public Menu menu;

   public java.util.Collection<WorkSchedule> schedule;

   public java.util.Collection<Order> orders;

   public java.util.Collection<RestorauntManager> managers;

   public DrinksMenu drinks;

   public java.util.Collection<RestaurantRating> ratings;
   
   public java.util.Collection<Employee> getEmployees() {
      if (employees == null)
         employees = new java.util.ArrayList<Employee>();
      return employees;
   }
   
   public java.util.Iterator getIteratorEmployees() {
      if (employees == null)
         employees = new java.util.ArrayList<Employee>();
      return employees.iterator();
   }
   
   public void setEmployees(java.util.Collection<Employee> newEmployees) {
      removeAllEmployees();
      for (java.util.Iterator iter = newEmployees.iterator(); iter.hasNext();)
         addEmployees((Employee)iter.next());
   }
   
   public void addEmployees(Employee newEmployee) {
      if (newEmployee == null)
         return;
      if (this.employees == null)
         this.employees = new java.util.ArrayList<Employee>();
      if (!this.employees.contains(newEmployee))
      {
         this.employees.add(newEmployee);
         //newEmployee.setRestaurant(this);      
      }
   }
   
   public void removeEmployees(Employee oldEmployee) {
      if (oldEmployee == null)
         return;
      if (this.employees != null)
         if (this.employees.contains(oldEmployee))
         {
            this.employees.remove(oldEmployee);
            //oldEmployee.setRestaurant((Restaurant)null);
         }
   }
   
   public void removeAllEmployees() {
      if (employees != null)
      {
         Employee oldEmployee;
         for (java.util.Iterator iter = getIteratorEmployees(); iter.hasNext();)
         {
            oldEmployee = (Employee)iter.next();
            iter.remove();
            //oldEmployee.setRestaurant((Restaurant)null);
         }
      }
   }

   public java.util.Collection<WorkSchedule> getSchedule() {
      if (schedule == null)
         schedule = new java.util.ArrayList<WorkSchedule>();
      return schedule;
   }
   
   public java.util.Iterator getIteratorSchedule() {
      if (schedule == null)
         schedule = new java.util.ArrayList<WorkSchedule>();
      return schedule.iterator();
   }
   
   public void setSchedule(java.util.Collection<WorkSchedule> newSchedule) {
      removeAllSchedule();
      for (java.util.Iterator iter = newSchedule.iterator(); iter.hasNext();)
         addSchedule((WorkSchedule)iter.next());
   }
   
   public void addSchedule(WorkSchedule newWorkSchedule) {
      if (newWorkSchedule == null)
         return;
      if (this.schedule == null)
         this.schedule = new java.util.ArrayList<WorkSchedule>();
      if (!this.schedule.contains(newWorkSchedule))
      {
         this.schedule.add(newWorkSchedule);
         newWorkSchedule.setRestaurant(this);      
      }
   }
   
   public void removeSchedule(WorkSchedule oldWorkSchedule) {
      if (oldWorkSchedule == null)
         return;
      if (this.schedule != null)
         if (this.schedule.contains(oldWorkSchedule))
         {
            this.schedule.remove(oldWorkSchedule);
            oldWorkSchedule.setRestaurant((Restaurant)null);
         }
   }
   
   public void removeAllSchedule() {
      if (schedule != null)
      {
         WorkSchedule oldWorkSchedule;
         for (java.util.Iterator iter = getIteratorSchedule(); iter.hasNext();)
         {
            oldWorkSchedule = (WorkSchedule)iter.next();
            iter.remove();
            oldWorkSchedule.setRestaurant((Restaurant)null);
         }
      }
   }

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
      {
         this.orders.add(newOrder);
         newOrder.setRestaurant(this);      
      }
   }
   
   public void removeOrders(Order oldOrder) {
      if (oldOrder == null)
         return;
      if (this.orders != null)
         if (this.orders.contains(oldOrder))
         {
            this.orders.remove(oldOrder);
            oldOrder.setRestaurant((Restaurant)null);
         }
   }
   
   public void removeAllOrders() {
      if (orders != null)
      {
         Order oldOrder;
         for (java.util.Iterator iter = getIteratorOrders(); iter.hasNext();)
         {
            oldOrder = (Order)iter.next();
            iter.remove();
            oldOrder.setRestaurant((Restaurant)null);
         }
      }
   }

   public java.util.Collection<RestorauntManager> getManagers() {
      if (managers == null)
         managers = new java.util.ArrayList<RestorauntManager>();
      return managers;
   }
   
   public java.util.Iterator getIteratorManagers() {
      if (managers == null)
         managers = new java.util.ArrayList<RestorauntManager>();
      return managers.iterator();
   }
   
   public void setManagers(java.util.Collection<RestorauntManager> newManagers) {
      removeAllManagers();
      for (java.util.Iterator iter = newManagers.iterator(); iter.hasNext();)
         addManagers((RestorauntManager)iter.next());
   }
   
   public void addManagers(RestorauntManager newRestorauntManager) {
      if (newRestorauntManager == null)
         return;
      if (this.managers == null)
         this.managers = new java.util.ArrayList<RestorauntManager>();
      if (!this.managers.contains(newRestorauntManager))
      {
         this.managers.add(newRestorauntManager);
         //newRestorauntManager.setRestoraunt(this);      
      }
   }
   
   public void removeManagers(RestorauntManager oldRestorauntManager) {
      if (oldRestorauntManager == null)
         return;
      if (this.managers != null)
         if (this.managers.contains(oldRestorauntManager))
         {
            this.managers.remove(oldRestorauntManager);
            //oldRestorauntManager.setRestoraunt((Restaurant)null);
         }
   }
   
   public void removeAllManagers() {
      if (managers != null)
      {
         RestorauntManager oldRestorauntManager;
         for (java.util.Iterator iter = getIteratorManagers(); iter.hasNext();)
         {
            oldRestorauntManager = (RestorauntManager)iter.next();
            iter.remove();
            //oldRestorauntManager.setRestoraunt((Restaurant)null);
         }
      }
   }

   public java.util.Collection<RestaurantRating> getRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<RestaurantRating>();
      return ratings;
   }
   
   public java.util.Iterator getIteratorRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<RestaurantRating>();
      return ratings.iterator();
   }
   
   public void setRatings(java.util.Collection<RestaurantRating> newRatings) {
      removeAllRatings();
      for (java.util.Iterator iter = newRatings.iterator(); iter.hasNext();)
         addRatings((RestaurantRating)iter.next());
   }
   
   public void addRatings(RestaurantRating newRestaurantRating) {
      if (newRestaurantRating == null)
         return;
      if (this.ratings == null)
         this.ratings = new java.util.ArrayList<RestaurantRating>();
      if (!this.ratings.contains(newRestaurantRating))
      {
         this.ratings.add(newRestaurantRating);
         newRestaurantRating.setRestaurant(this);      
      }
   }
   
   public void removeRatings(RestaurantRating oldRestaurantRating) {
      if (oldRestaurantRating == null)
         return;
      if (this.ratings != null)
         if (this.ratings.contains(oldRestaurantRating))
         {
            this.ratings.remove(oldRestaurantRating);
            oldRestaurantRating.setRestaurant((Restaurant)null);
         }
   }
   
   public void removeAllRatings() {
      if (ratings != null)
      {
         RestaurantRating oldRestaurantRating;
         for (java.util.Iterator iter = getIteratorRatings(); iter.hasNext();)
         {
            oldRestaurantRating = (RestaurantRating)iter.next();
            iter.remove();
            oldRestaurantRating.setRestaurant((Restaurant)null);
         }
      }
   }
*/
}