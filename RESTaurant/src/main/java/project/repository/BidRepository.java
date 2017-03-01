package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Bid;

public interface BidRepository extends Repository<Bid, Long>{
	public Bid save(Bid bid);
	
	@Modifying
	@Query("delete from Bid b where b.idBid = ?1")
	public void removeBidById(Long id);
	
	@Query("select b from Bid b where b.idBid =?1")
	public Bid findBidById(Long id);
	
	@Query("select b from Bid b where b.manager.userID =?1")
	public List<Bid> findBidsByManager(Long id);
	
	@Query("select b from Bid b where b.manager.restaurant.restaurantID =?1")
	public List<Bid> findBidsByRestaurant(Long id);
	
	@Query("select b from Bid b")
	public List<Bid> findAllBids();
}
