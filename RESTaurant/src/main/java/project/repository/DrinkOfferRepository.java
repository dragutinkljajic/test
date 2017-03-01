package project.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.DrinkOffer;

public interface DrinkOfferRepository extends Repository<DrinkOffer, Long>{
	public DrinkOffer save(DrinkOffer drinkOffer);
	
	@Modifying
	@Query("delete from DrinkOffer d where d.offer.idOffer = ?1")
	public void removeDrinkOfferByOfferId(Long id);
}
