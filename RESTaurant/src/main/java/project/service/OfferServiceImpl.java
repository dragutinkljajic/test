package project.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Offer;
import project.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService{
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Override
	public Offer addOffer(Offer offer) {
		return offerRepository.save(offer);
	}

	@Override
	public void deleteOfferById(Long id) {
		offerRepository.removeOfferById(id);
	}

	@Override
	public List<Offer> getAllOffers() {
		return offerRepository.findAllOffers();
	}

	@Override
	public List<Offer> getOffersByManagerId(Long id) {
		return offerRepository.findOffersByManagerId(id);
	}

	@Override
	public List<Offer> getOffersBySupplierId(Long id) {
		return offerRepository.findOffersBySupplierId(id);
	}

	@Override
	public void updateDetails(Long id, Date delivery, Date warranty, Date lastsUntil) {
		offerRepository.updateDetails(id, delivery, warranty, lastsUntil);
	}

	@Override
	public Offer getOfferById(Long id) {
		return offerRepository.findOfferById(id);
	}

	@Override
	public List<Offer> getOffersByBidId(Long id) {
		return offerRepository.findOffersByBidId(id);
	}

}
