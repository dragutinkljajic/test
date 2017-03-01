/***********************************************************************
 * Module:  Segment.java
 * Author:  Bojan
 * Purpose: Defines the Class Segment
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Segment {
	
	@Id
	@GeneratedValue
	@Column(name="segment_id", nullable = false)
	public long idSegment;
   
	@Column(name="segment_label", nullable = false)
	public String label;
   
	@Column(name="segment_description")
	public String description;
	
	@Column(name="segment_rows", nullable = false)
	public long tableRows;
	
	@Column(name="segment_cols", nullable = false)
	public long tableColumns;
   
	@OneToMany(mappedBy="segment")
	@JsonIgnore
	public Set<RestTable> tables;
	
	@ManyToOne
	@JoinColumn(name="seating_arrangement_id")
	public SeatingArrangement seating;
	
	@OneToMany(mappedBy="segment")
	@JsonIgnore
	public Set<Shift> shifts;
	
	public Segment() {}

	public long getIdSegment() {
		return idSegment;
	}

	public void setIdSegment(long idSegment) {
		this.idSegment = idSegment;
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

	public SeatingArrangement getSeating() {
		return seating;
	}

	public void setSeating(SeatingArrangement seating) {
		this.seating = seating;
	}
   /*
   public java.util.Collection<Table> getTables() {
      if (tables == null)
         tables = new java.util.ArrayList<Table>();
      return tables;
   }
   
   public java.util.Iterator getIteratorTables() {
      if (tables == null)
         tables = new java.util.ArrayList<Table>();
      return tables.iterator();
   }
   
   public void setTables(java.util.Collection<Table> newTables) {
      removeAllTables();
      for (java.util.Iterator iter = newTables.iterator(); iter.hasNext();)
         addTables((Table)iter.next());
   }
   
   public void addTables(Table newTable) {
      if (newTable == null)
         return;
      if (this.tables == null)
         this.tables = new java.util.ArrayList<Table>();
      if (!this.tables.contains(newTable))
         this.tables.add(newTable);
   }
   
   public void removeTables(Table oldTable) {
      if (oldTable == null)
         return;
      if (this.tables != null)
         if (this.tables.contains(oldTable))
            this.tables.remove(oldTable);
   }
   
   public void removeAllTables() {
      if (tables != null)
         tables.clear();
   }
   public SeatingArrangement getSeating() {
      return seating;
   }
   public void setSeating(SeatingArrangement newSeatingArrangement) {
      if (this.seating == null || !this.seating.equals(newSeatingArrangement))
      {
         if (this.seating != null)
         {
            SeatingArrangement oldSeatingArrangement = this.seating;
            this.seating = null;
            oldSeatingArrangement.removeSegments(this);
         }
         if (newSeatingArrangement != null)
         {
            this.seating = newSeatingArrangement;
            this.seating.addSegments(this);
         }
      }
   }
	*/
}