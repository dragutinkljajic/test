/***********************************************************************
 * Module:  Table.java
 * Author:  Bojan
 * Purpose: Defines the Class Table
 ***********************************************************************/
package project.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RestTable {
	@Id
	@GeneratedValue
	@Column(name="table_id", nullable = false)
	public long idTable;
      
	@Column(name="table_code", nullable = false)
	public String tableCode;
	
	@Column(name="table_status", nullable = false)
	public String status;
	
	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	@Column(name="table_row", nullable = false)
	public int tableRow;
	
	@Column(name="table_col", nullable = false)
	public int tableCol;
	
	public int getTableRow() {
		return tableRow;
	}

	public void setTableRow(int tableRow) {
		this.tableRow = tableRow;
	}

	public int getTableCol() {
		return tableCol;
	}

	public void setTableCol(int tableCol) {
		this.tableCol = tableCol;
	}

	@ManyToOne
	@JoinColumn(name="segment_id")
	public Segment segment;
    
	@ManyToMany(mappedBy = "tables")
	@JsonIgnore
    public List<Reservation> reservations;
	
	@OneToMany
	@JsonIgnore
	public List<RestOrder> orders;
	
	public RestTable() {}
   

	public long getIdTable() {
		return idTable;
	}

	public void setIdTable(long idTable) {
		this.idTable = idTable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<RestOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<RestOrder> orders) {
		this.orders = orders;
	}
   
    
}