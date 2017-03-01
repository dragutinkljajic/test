/***********************************************************************
 * Module:  Shift.java
 * Author:  Bojan
 * Purpose: Defines the Class Shift
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Shift {
	
	@Id
	@GeneratedValue
	@Column(name="shift_id", nullable = false)
	public long idShift;
	
	@Column(name="shift_begins", nullable = false)
	public Date shiftBegins;
   
	@Column(name="shift_ends", nullable = false)
	public Date shiftEnds;
   
	@ManyToOne
	@JoinColumn(name="usr_id")
	public Employee employee;
   
	@ManyToOne
	@JoinColumn(name="segment_id")
	public Segment segment;

	@ManyToOne
	@JoinColumn(name="schedule_id")
	public WorkSchedule schedule;
	
	public WorkSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(WorkSchedule schedule) {
		this.schedule = schedule;
	}

	public Shift() {}

	public long getIdShift() {
		return idShift;
	}

	public void setIdShift(long idShift) {
		this.idShift = idShift;
	}

	public Date getShiftBegins() {
		return shiftBegins;
	}

	public void setShiftBegins(Date shiftBegins) {
		this.shiftBegins = shiftBegins;
	}

	public Date getShiftEnds() {
		return shiftEnds;
	}

	public void setShiftEnds(Date shiftEnds) {
		this.shiftEnds = shiftEnds;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}
}