package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.GroceryOffer;
import project.repository.GroceryOfferRepository;

@Service
public class GroceryOfferServiceImpl implements GroceryOfferService{
	
	@Autowired
	private GroceryOfferRepository groceryOfferRepository;
	
	@Override
	public GroceryOffer addGroceryOffer(GroceryOffer groceryOffer) {
		return groceryOfferRepository.save(groceryOffer);
	}

	@Override
	public void removeGroceryOfferByOfferId(Long id) {
		groceryOfferRepository.removeGroceryOfferByOfferId(id);
	}

}
