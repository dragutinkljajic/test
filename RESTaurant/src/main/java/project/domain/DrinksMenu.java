/***********************************************************************
 * Module:  DrinksMenu.java
 * Author:  Bojan
 * Purpose: Defines the Class DrinksMenu
 ***********************************************************************/
package project.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DrinksMenu {

	@Id
	@GeneratedValue
	@Column(name="drinks_menu_id", nullable = false)
	public long idDrinkMenu;

	@OneToOne
	@JoinColumn(name="restaurant")
	@JsonIgnore
	public Restaurant restaurant;
	
	@OneToMany(mappedBy="drinksMenu")
	@JsonIgnore
	public Set<Drink> drinks;
	
	public DrinksMenu() {}

	public long getIdDrinkMenu() {
		return idDrinkMenu;
	}

	public void setIdDrinkMenu(long idDrinkMenu) {
		this.idDrinkMenu = idDrinkMenu;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Set<Drink> getDrinks() {
		return drinks;
	}

	public void setDrinks(Set<Drink> drinks) {
		this.drinks = drinks;
	}
   /*
   public java.util.Collection<Drink> getDrinks() {
      if (drinks == null)
         drinks = new java.util.ArrayList<Drink>();
      return drinks;
   }
   
   public java.util.Iterator getIteratorDrinks() {
      if (drinks == null)
         drinks = new java.util.ArrayList<Drink>();
      return drinks.iterator();
   }
   
   public void setDrinks(java.util.Collection<Drink> newDrinks) {
      removeAllDrinks();
      for (java.util.Iterator iter = newDrinks.iterator(); iter.hasNext();)
         addDrinks((Drink)iter.next());
   }
   
   public void addDrinks(Drink newDrink) {
      if (newDrink == null)
         return;
      if (this.drinks == null)
         this.drinks = new java.util.ArrayList<Drink>();
      if (!this.drinks.contains(newDrink))
      {
         this.drinks.add(newDrink);
         newDrink.addDrinkMenu(this);      
      }
   }
   
   public void removeDrinks(Drink oldDrink) {
      if (oldDrink == null)
         return;
      if (this.drinks != null)
         if (this.drinks.contains(oldDrink))
         {
            this.drinks.remove(oldDrink);
            oldDrink.removeDrinkMenu(this);
         }
   }
   
   public void removeAllDrinks() {
      if (drinks != null)
      {
         Drink oldDrink;
         for (java.util.Iterator iter = getIteratorDrinks(); iter.hasNext();)
         {
            oldDrink = (Drink)iter.next();
            iter.remove();
            oldDrink.removeDrinkMenu(this);
         }
      }
   }
   */
}