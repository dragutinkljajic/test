package project.service;

import java.util.List;

import project.domain.Bid;

public interface BidService {
	Bid addBid(Bid bid);
	
	List<Bid> getBidsByManagerId(Long id);
	
	List<Bid> getBidsByRestaurantId(Long id);
	
	void deleteBidById(Long id);
	
	List<Bid> getAllBids();
	
	Bid getBid(Long id);
}
