package project.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.GroceryOffer;

public interface GroceryOfferRepository extends Repository<GroceryOffer, Long>{
	public GroceryOffer save(GroceryOffer groceryOffer);
	
	@Modifying
	@Query("delete from GroceryOffer g where g.offer.idOffer = ?1")
	public void removeGroceryOfferByOfferId(Long id);
}
