package project.service;

import java.util.Date;
import java.util.List;

import project.domain.Offer;

public interface OfferService {
	Offer addOffer(Offer offer);
		
	void deleteOfferById(Long id);
	
	List<Offer> getAllOffers();
	
	List<Offer> getOffersByManagerId(Long id);
	
	List<Offer> getOffersBySupplierId(Long id);
	
	List<Offer> getOffersByBidId(Long id);
	
	Offer getOfferById(Long id);
	
	void updateDetails(Long id, Date delivery, Date warranty, Date lastsUntil);
}
