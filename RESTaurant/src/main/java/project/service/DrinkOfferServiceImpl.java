package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.DrinkOffer;
import project.repository.DrinkOfferRepository;

@Service
public class DrinkOfferServiceImpl implements DrinkOfferService{
	
	@Autowired
	private DrinkOfferRepository drinkOfferRepository;
	
	@Override
	public DrinkOffer addDrinkOffer(DrinkOffer drinkOffer) {
		return drinkOfferRepository.save(drinkOffer);
	}

	@Override
	public void removeDrinkOfferByOfferId(Long id) {
		drinkOfferRepository.removeDrinkOfferByOfferId(id);
	}

}
