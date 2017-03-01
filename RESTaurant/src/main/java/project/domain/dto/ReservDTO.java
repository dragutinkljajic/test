package project.domain.dto;

import java.util.Date;
import java.util.List;

import project.domain.Reservation;

public class ReservDTO {

	private Long reservID;
	
	private Long userID;
	
	private Long restID;
	
	private Long segmentID;
	
	private List<String> tableCodess;
	
	private String status;
	
	private Date date;
	
	private Date time;
	
	private short duration;
	
	private String currentTableCode;
	
	private List<Long> drinks;
	
	private List<Long> dishes;
	
	private boolean onArrival;
	
	public ReservDTO() {}

	public ReservDTO(Reservation r) {
		this.reservID = r.getId();
		this.userID = r.getCustomer().getUserID();
		this.restID = r.getRestaurant().getRestaurantID();
		this.status = r.getStatus();
		this.date = r.getDate();
		this.time = r.getTime();
		this.duration = r.getDuration();
	}
	
	
	public Long getReservID() {
		return reservID;
	}

	public void setReservID(Long reservID) {
		this.reservID = reservID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getRestID() {
		return restID;
	}

	public void setRestID(Long restID) {
		this.restID = restID;
	}

	public Long getSegmentID() {
		return segmentID;
	}

	public void setSegmentID(Long segmentID) {
		this.segmentID = segmentID;
	}

	public List<String> getTableCodess() {
		return tableCodess;
	}

	public void setTableCodess(List<String> tableCodess) {
		this.tableCodess = tableCodess;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public short getDuration() {
		return duration;
	}

	public void setDuration(short duration) {
		this.duration = duration;
	}

	public String getCurrentTableCode() {
		return currentTableCode;
	}

	public void setCurrentTableCode(String currentTableCode) {
		this.currentTableCode = currentTableCode;
	}

	public List<Long> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<Long> drinks) {
		this.drinks = drinks;
	}

	public List<Long> getDishes() {
		return dishes;
	}

	public void setDishes(List<Long> dishes) {
		this.dishes = dishes;
	}

	public boolean isOnArrival() {
		return onArrival;
	}

	public void setOnArrival(boolean onArrival) {
		this.onArrival = onArrival;
	}

	
}
