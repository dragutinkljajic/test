package project.domain.dto;

import java.util.Set;

import project.domain.Bid;
import project.domain.DrinkOffer;
import project.domain.GroceryOffer;

public class OfferDTO {
	public long idOffer;
	
	public String delivery;
	
	public String warranty;
	
	public String lastsUntil;
	
	public BidDTO bid;
	
	public Set<DrinkOffer> drinkOffers;
	
	public Set<GroceryOffer> groceryOffers;
	
	public OfferDTO() {}

	public long getIdOffer() {
		return idOffer;
	}

	public void setIdOffer(long idOffer) {
		this.idOffer = idOffer;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getLastsUntil() {
		return lastsUntil;
	}

	public void setLastsUntil(String lastsUntil) {
		this.lastsUntil = lastsUntil;
	}

	public BidDTO getBid() {
		return bid;
	}

	public void setBid(BidDTO bid) {
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
}
