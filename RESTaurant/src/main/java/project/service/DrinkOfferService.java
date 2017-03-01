package project.service;

import project.domain.DrinkOffer;

public interface DrinkOfferService {
	DrinkOffer addDrinkOffer(DrinkOffer drinkOffer);
	
	void removeDrinkOfferByOfferId(Long id);
}
