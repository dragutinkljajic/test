package project.service;

import project.domain.GroceryOffer;

public interface GroceryOfferService {
	GroceryOffer addGroceryOffer(GroceryOffer groceryOffer);
	
	void removeGroceryOfferByOfferId(Long id);
}
