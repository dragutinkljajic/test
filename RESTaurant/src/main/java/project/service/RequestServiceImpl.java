package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import project.domain.Customer;
import project.domain.Request;
import project.repository.RequestRepository;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestRepository requestRepository;
	
	@Override
	public List<Request> getAll() {
		return requestRepository.findAll();
	}

	@Override
	public Request getRequest(Long id) {
		Assert.notNull(id, "ID cannot be null.");
		return requestRepository.findRequestById(id);
	}

	@Override
	public List<Request> getAllBySender(Customer cst) {
		Assert.notNull(cst, "Customer cannot be null");
		return requestRepository.findRequestsBySender(cst.getUserID());
	}

	@Override
	public List<Request> getAllByReceiver(Customer cst) {
		Assert.notNull(cst, "Customer cannot be null");
		return requestRepository.findRequestsByReceiver(cst.getUserID());
	}

	@Override
	public Request save(Request request) {
		Assert.notNull(request, "Request cannot be null");
		return requestRepository.save(request);
	}

	@Override
	public List<Request> getAllByCombination(Customer sender, Customer receiver) {
		Assert.notNull(sender, "Customer cannot be null");
		return requestRepository.findRequestsByCombination(sender.getUserID(), receiver.getUserID());
	}

}
