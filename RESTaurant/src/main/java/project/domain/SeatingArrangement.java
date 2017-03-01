/***********************************************************************
 * Module:  SeatingArrangement.java
 * Author:  Bojan
 * Purpose: Defines the Class SeatingArrangement
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
public class SeatingArrangement {
	
	@Id
	@GeneratedValue
	@Column(name="seating_arrangement_id", nullable = false)
	public long idSeating;
   
	@OneToMany(mappedBy="seating")
	@JsonIgnore
	public Set<Segment> segments;
	
	@OneToOne
	@JoinColumn(name="restaurant")
	@JsonIgnore
	public Restaurant restaurant;
	
	public SeatingArrangement() {}

	public long getIdSeating() {
		return idSeating;
	}

	public void setIdSeating(long idSeating) {
		this.idSeating = idSeating;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
   /*
   public java.util.Collection<Segment> getSegments() {
      if (segments == null)
         segments = new java.util.ArrayList<Segment>();
      return segments;
   }
   
   public java.util.Iterator getIteratorSegments() {
      if (segments == null)
         segments = new java.util.ArrayList<Segment>();
      return segments.iterator();
   }
   
   public void setSegments(java.util.Collection<Segment> newSegments) {
      removeAllSegments();
      for (java.util.Iterator iter = newSegments.iterator(); iter.hasNext();)
         addSegments((Segment)iter.next());
   }
   
   public void addSegments(Segment newSegment) {
      if (newSegment == null)
         return;
      if (this.segments == null)
         this.segments = new java.util.ArrayList<Segment>();
      if (!this.segments.contains(newSegment))
      {
         this.segments.add(newSegment);
         newSegment.setSeating(this);      
      }
   }
   
   public void removeSegments(Segment oldSegment) {
      if (oldSegment == null)
         return;
      if (this.segments != null)
         if (this.segments.contains(oldSegment))
         {
            this.segments.remove(oldSegment);
            oldSegment.setSeating((SeatingArrangement)null);
         }
   }
   
   public void removeAllSegments() {
      if (segments != null)
      {
         Segment oldSegment;
         for (java.util.Iterator iter = getIteratorSegments(); iter.hasNext();)
         {
            oldSegment = (Segment)iter.next();
            iter.remove();
            oldSegment.setSeating((SeatingArrangement)null);
         }
      }
   }
   */
}