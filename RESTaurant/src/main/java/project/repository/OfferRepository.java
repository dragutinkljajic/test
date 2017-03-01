package project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Offer;

public interface OfferRepository extends Repository<Offer, Long>{
	public Offer save(Offer offer);
	
	@Modifying
	@Query("delete from Offer o where o.idOffer = ?1")
	public void removeOfferById(Long id);
	
	@Query("select o from Offer o")
	public List<Offer> findAllOffers();
	
	@Query("select o from Offer o where o.bid.manager.restaurant.restaurantID = ?1")
	public List<Offer> findOffersByManagerId(Long id);
	
	@Query("select o from Offer o where o.bid.idBid = ?1")
	public List<Offer> findOffersByBidId(Long id);
	
	@Query("select o from Offer o where o.supplier.userID = ?1")
	public List<Offer> findOffersBySupplierId(Long id);
	
	@Query("select o from Offer o where o.idOffer = ?1")
	public Offer findOfferById(Long id);
	
	@Modifying
	@Query("update Offer o set o.delivery = ?2, o.warranty = ?3, o.lastsUntil = ?4 where o.idOffer = ?1")
	public void updateDetails(Long id, Date delivery, Date warranty, Date lastsUntil);
}
