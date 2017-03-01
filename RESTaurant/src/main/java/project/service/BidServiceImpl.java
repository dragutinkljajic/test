package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Bid;
import project.repository.BidRepository;

@Service
public class BidServiceImpl implements BidService{

	@Autowired
	private BidRepository bidRepository;
	
	@Override
	public Bid addBid(Bid bid) {
		return bidRepository.save(bid);
	}

	@Override
	public List<Bid> getBidsByManagerId(Long id) {
		return bidRepository.findBidsByManager(id);
	}

	@Override
	public void deleteBidById(Long id) {
		bidRepository.removeBidById(id);
	}

	@Override
	public List<Bid> getAllBids() {
		return bidRepository.findAllBids();
	}

	@Override
	public Bid getBid(Long id) {
		return bidRepository.findBidById(id);
	}

	@Override
	public List<Bid> getBidsByRestaurantId(Long id) {
		return bidRepository.findBidsByRestaurant(id);
	}

}
