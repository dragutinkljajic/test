/***********************************************************************
 * Module:  Menu.java
 * Author:  Bojan
 * Purpose: Defines the Class Menu
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Menu {
	
	@Id
	@GeneratedValue
	@Column(name="menu_id", nullable = false)
	public long idMenu;
   
	//public java.util.Collection<Dish> dishes;
   
	@OneToOne
	@JoinColumn(name="restaurant")
	@JsonIgnore
	public Restaurant restaurant;
	
	public Menu(){}

	public long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(long idMenu) {
		this.idMenu = idMenu;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	@OneToMany(mappedBy="menu")
	@JsonIgnore
	public Set<Dish> dishes;
	/*
   public java.util.Collection<Dish> getDishes() {
      if (dishes == null)
         dishes = new java.util.ArrayList<Dish>();
      return dishes;
   }
   
   public java.util.Iterator getIteratorDishes() {
      if (dishes == null)
         dishes = new java.util.ArrayList<Dish>();
      return dishes.iterator();
   }
   
   public void setDishes(java.util.Collection<Dish> newDishes) {
      removeAllDishes();
      for (java.util.Iterator iter = newDishes.iterator(); iter.hasNext();)
         addDishes((Dish)iter.next());
   }
   
   public void addDishes(Dish newDish) {
      if (newDish == null)
         return;
      if (this.dishes == null)
         this.dishes = new java.util.ArrayList<Dish>();
      if (!this.dishes.contains(newDish))
      {
         this.dishes.add(newDish);
         newDish.addMenu(this);      
      }
   }
   
   public void removeDishes(Dish oldDish) {
      if (oldDish == null)
         return;
      if (this.dishes != null)
         if (this.dishes.contains(oldDish))
         {
            this.dishes.remove(oldDish);
            oldDish.removeMenu(this);
         }
   }
   
   public void removeAllDishes() {
      if (dishes != null)
      {
         Dish oldDish;
         for (java.util.Iterator iter = getIteratorDishes(); iter.hasNext();)
         {
            oldDish = (Dish)iter.next();
            iter.remove();
            oldDish.removeMenu(this);
         }
      }
   }
	 */
}