/***********************************************************************
 * Module:  ServiceRating.java
 * Author:  Dusan hehe
 * Purpose: Defines the Class ServiceRating
 ***********************************************************************/
package project.domain;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class ServiceRating {

	@Id
	@GeneratedValue
   @Column(name = "svcr_id", nullable = false)
   public long idServiceRaing;
   
   @Column(name = "svcr_rate", nullable = false)
   public int rating;
   

@Column(name = "svcr_wtr", nullable = false)
   public Waiter waiter;
   
   @Column(name = "svcr_chfs", nullable = true)
   public List<Chef> chefs;
   
   @Column(name = "svcr_bar", nullable = true)
   public List<Bartender> bartenders;
   
   @Column(name = "svr_rsto", nullable = false)
   public RestOrder restOrder;


public RestOrder getRestOrder() {
	return restOrder;
}

public void setRestOrder(RestOrder restOrder) {
	this.restOrder = restOrder;
	if(chefs!=null){
		chefs.clear();
	}
	if(restOrder.getDishOrders()!=null){
		for (int i=0; i<restOrder.getDishOrders().size(); i++){
			DishOrder meal = restOrder.getDishOrders().get(i);
			Chef newChef = meal.getChef();
			if(!chefs.contains(newChef)){
				chefs.add(newChef);
			}
		}
	}
	if(bartenders!=null){
		bartenders.clear();
	}
	if(restOrder.getDrinkOrders()!=null){
		for (int i=0; i<restOrder.getDrinkOrders().size(); i++){
			DrinkOrder drink = restOrder.getDrinkOrders().get(i);
			Bartender barmen = drink.getBartender();
			if(!bartenders.contains(barmen)){
				bartenders.add(barmen);
			}
		}
	}
}

public long getIdServiceRaing() {
	return idServiceRaing;
}

public void setIdServiceRaing(long idServiceRaing) {
	this.idServiceRaing = idServiceRaing;
}

public Waiter getWaiter() {
	return waiter;
}

public void setWaiter(Waiter waiter) {
	this.waiter = waiter;
}

public List<Chef> getChefs() {
	return chefs;
}

public void setChefs(List<Chef> chefs) {
	this.chefs = chefs;
}

public List<Bartender> getBartender() {
	return bartenders;
}

public void setBartender(List<Bartender> bartenders) {
	this.bartenders = bartenders;
}

  

public int getRating() {
	return rating;
}

public void setRating(int rating) {
	this.rating = rating;
	if(bartenders!=null){
		for(int i=0; i<bartenders.size();i++){
			bartenders.get(i).addRating(rating);
		}
	}
	if(chefs!=null){
		for(int i=0; i<chefs.size();i++){
			chefs.get(i).addRating(rating);
		}
	}
	waiter.addRating(rating);
}
   

}