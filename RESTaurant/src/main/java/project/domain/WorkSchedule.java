/***********************************************************************
 * Module:  WorkSchedule.java
 * Author:  Bojan
 * Purpose: Defines the Class WorkSchedule
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class WorkSchedule {
	
	@Id
	@GeneratedValue
	@Column(name="schedule_id", nullable = false)
	public long idSchedule;
   
   	@OneToOne
	@JoinColumn(name="restaurant")
	@JsonIgnore
	public Restaurant restaurant;
   
   	@OneToMany(mappedBy="schedule")
	@JsonIgnore
   	public Set<Shift> shifts;
   	
   	public WorkSchedule() {}

	public long getIdSchedule() {
		return idSchedule;
	}

	public void setIdSchedule(long idSchedule) {
		this.idSchedule = idSchedule;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
}