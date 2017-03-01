/***********************************************************************
 * Module:  Grocery.java
 * Author:  Bojan
 * Purpose: Defines the Class Grocery
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Grocery {
	@Id
	@GeneratedValue
	@Column(name="grocery_id", nullable = false)
	public long idGrocery;
	
	@Column(name="grocery_label", nullable = false)
	public String label;
	
	@Column(name="grocery_description")
	public String description;
	
	@ManyToMany(mappedBy = "groceries")
	@JsonIgnore
	public Set<Bid> bids;
	
	@OneToMany(mappedBy="grocery")
	@JsonIgnore
	public Set<GroceryOffer> groceryOffers;
	
	public Grocery() {}

	public long getIdGrocery() {
		return idGrocery;
	}

	public void setIdGrocery(long idGrocery) {
		this.idGrocery = idGrocery;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}
}