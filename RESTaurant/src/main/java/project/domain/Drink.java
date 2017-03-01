package project.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Drink {
	@Id
	@GeneratedValue
	@Column(name="drink_id", nullable = false)
	public long idDrink;
	
	@Column(name="drink_label", nullable = false)
	public String label;
	
	@Column(name="drink_description")
	public String description;
	
	@Column(name="drink_price", nullable = false)
	public float price;
	
	@ManyToOne
	@JoinColumn(name="drinks_menu_id")
	public DrinksMenu drinksMenu;
	
	@ManyToMany(mappedBy = "drinks")
	@JsonIgnore
	public Set<Bid> bids;
	
	@OneToMany(mappedBy="drink")
	@JsonIgnore
	public Set<DrinkOffer> drinkOffers;
	
	@OneToMany(mappedBy="drink")
	@JsonIgnore
    private List<DrinkOrder> drinkOrders;
	
	public Drink() {}

	public long getIdDrink() {
		return idDrink;
	}

	public void setIdDrink(long idDrink) {
		this.idDrink = idDrink;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<DrinkOrder> getDrinkOrders() {
		return drinkOrders;
	}

	public void setDrinkOrders(List<DrinkOrder> drinkOrders) {
		this.drinkOrders = drinkOrders;
	}

	public DrinksMenu getDrinksMenu() {
		return drinksMenu;
	}

	public void setDrinksMenu(DrinksMenu drinksMenu) {
		this.drinksMenu = drinksMenu;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public Set<DrinkOffer> getDrinkOffers() {
		return drinkOffers;
	}

	public void setDrinkOffers(Set<DrinkOffer> drinkOffers) {
		this.drinkOffers = drinkOffers;
	}
	
	public void addRating(int rating, Restaurant restaurant){
	}
	
	
	
   /*
   public java.util.Collection<DrinkRating> ratings;
   public java.util.Collection<DrinksMenu> drinkMenu;
   */
	
	
   
   
  
}