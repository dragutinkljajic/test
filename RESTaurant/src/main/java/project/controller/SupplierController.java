package project.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.domain.Bid;
import project.domain.DrinkOffer;
import project.domain.Grocery;
import project.domain.GroceryOffer;
import project.domain.Offer;
import project.domain.Online;
import project.domain.Supplier;
import project.domain.dto.BidDTO;
import project.domain.dto.OfferDTO;
import project.domain.dto.PasswordDTO;
import project.repository.BidRepository;
import project.repository.DrinkOfferRepository;
import project.repository.OfferRepository;
import project.service.BidService;
import project.service.DrinkOfferService;
import project.service.GroceryOfferService;
import project.service.OfferService;
import project.service.OnlineService;
import project.service.SupplierService;

@RequestMapping("/supplier")
@Controller
public class SupplierController {
	@Autowired
	private OnlineService onlineService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private BidService bidService;
	
	@Autowired
	private OfferService offerService;
	
	@Autowired
	private DrinkOfferService drinkOfferService;
	
	@Autowired
	private GroceryOfferService groceryOfferService;
	
	@RequestMapping(value = "/load",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Supplier> loadSupplier(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Supplier supplier = supplierService.getSupplierById(online.getUser().getUserID());
			supplier.setPassword("");
			return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
		} else {
			return null;
		}

		
	}
	
	@Transactional
	@RequestMapping(value = "updateEmail",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> updateEmail(@Context HttpServletRequest request, @RequestBody Supplier supplier) {
		
		if (supplier != null && !supplier.getEmail().equals("")) {
			if(supplierService.getSupplierByEmail(supplier.getEmail()) == null){
				supplierService.updateSupplierEmail(supplier);
				return new ResponseEntity<String>(supplier.getEmail(), HttpStatus.OK);
			}else{
				if(supplierService.getSupplierByEmail(supplier.getEmail()).getUserID().equals(supplier.getUserID())){
					return new ResponseEntity<String>("same", HttpStatus.OK);
				}
				return new ResponseEntity<String>("taken", HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>("No email sent", HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "updatePassword",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> updatePassword(@Context HttpServletRequest request, @RequestBody PasswordDTO dto) {
		
		if (dto != null && !dto.getCurrentPass().equals("") && !dto.getNewPass().equals("")) {
			Supplier tmp = supplierService.getSupplierById(dto.getUserID());
			if (tmp != null && tmp.getPassword().equals(dto.getCurrentPass())) {
				supplierService.updateSupplierPassword(dto.generateSupplier());
				return new ResponseEntity<String>("Password successfully changed", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Invalid password", HttpStatus.OK);
			}
			
		}
		
		return new ResponseEntity<String>("Invalid data sent", HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "updateDetails",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Supplier> updateDetails(@Context HttpServletRequest request, @RequestBody Supplier supplier) {
		
		if (supplier != null) {
			supplierService.updateSupplierDetails(supplier);
			return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
		}
		
		return new ResponseEntity<Supplier>(new Supplier(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getBids",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Bid> getBids(@Context HttpServletRequest request){
		
		return bidService.getAllBids();
	}
	
	@RequestMapping(value="/addOffer",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addOffer(@Context HttpServletRequest request, @RequestBody Offer offer) throws ParseException{
		
		Bid realBid = bidService.getBid(offer.getBid().idBid);
		
		if(realBid.hasOffer){
			System.out.println("pa imam vec");
			return "notOK";
		}
		
		offerService.addOffer(offer);
		
		for(DrinkOffer drinkOffer : offer.getDrinkOffers()){
			drinkOffer.setOffer(offer);
			drinkOfferService.addDrinkOffer(drinkOffer);
		}
		
		for(GroceryOffer groceryOffer : offer.getGroceryOffers()){
			groceryOffer.setOffer(offer);
			groceryOfferService.addGroceryOffer(groceryOffer);
		}
		
		return "OK";
	}
	
	@RequestMapping(value="/getOffers",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Offer> getOffers(@Context HttpServletRequest request, @RequestBody Supplier supplier){
		
		return offerService.getOffersBySupplierId(supplier.getUserID());
	}
	
	@Transactional
	@RequestMapping(value="/updateOffer",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String updateOffer(@Context HttpServletRequest request, @RequestBody Offer offer){
		
		if(offer.getBid().getEnd().after(new Date())){
		drinkOfferService.removeDrinkOfferByOfferId(offer.getIdOffer());
		groceryOfferService.removeGroceryOfferByOfferId(offer.getIdOffer());
		
		Offer realOffer = offerService.getOfferById(offer.getIdOffer());
		
		for(DrinkOffer drinkOffer : offer.getDrinkOffers()){
			drinkOffer.setOffer(realOffer);
			drinkOfferService.addDrinkOffer(drinkOffer);
		}
		
		for(GroceryOffer groceryOffer : offer.getGroceryOffers()){
			groceryOffer.setOffer(realOffer);
			groceryOfferService.addGroceryOffer(groceryOffer);
		}
		
		offerService.updateDetails(offer.getIdOffer(), offer.getDelivery(), offer.getWarranty(), offer.getLastsUntil());
		
		return "OK";
		}
		return "Too late to update offer.";
	}
}
