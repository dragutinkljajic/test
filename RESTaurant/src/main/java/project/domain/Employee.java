/***********************************************************************
 * Module:  Employee.java
 * Author:  Bojan
 * Purpose: Defines the Class Employee
 ***********************************************************************/
package project.domain;

import static javax.persistence.InheritanceType.JOINED;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = JOINED)
public class Employee extends User {

	private static final long serialVersionUID = 2783766729722638856L;

	@Column(name = "emp_name", nullable = false)
    private String name;

	@Column(name = "emp_surname", nullable = false)
    private String surname;

	@Column(name = "emp_role", nullable = false)
    private EmployeeRole role;

	@Column(name = "emp_pass_changed", nullable = false)
    private boolean passChanged = false;

	@Column(name = "emp_date_birth", nullable = false)
    private Date dateBirth;

	@Column(name = "emp_size_cloth", nullable = false)
    private int sizeCloth;

	@Column(name = "emp_size_shoes", nullable = false)
    private int sizeShoes;
	
	@OneToMany(mappedBy="employee")
	@JsonIgnore
	private Set<Shift> shifts;
	
	public Employee() {}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public EmployeeRole getRole() {
		return role;
	}

	public void setRole(EmployeeRole role) {
		this.role = role;
	}

	public boolean isPassChanged() {
		return passChanged;
	}

	public void setPassChanged(boolean passChanged) {
		this.passChanged = passChanged;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public int getSizeCloth() {
		return sizeCloth;
	}

	public void setSizeCloth(int sizeCloth) {
		this.sizeCloth = sizeCloth;
	}

	public int getSizeShoes() {
		return sizeShoes;
	}

	public void setSizeShoes(int sizeShoes) {
		this.sizeShoes = sizeShoes;
	}
	
	@ManyToOne
	@JoinColumn(name="rst_id")
	public Restaurant restaurant;

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
   
   /*
   public Restaurant getRestaurant() {
      return restaurant;
   }
   
   public void setRestaurant(Restaurant newRestaurant) {
      if (this.restaurant == null || !this.restaurant.equals(newRestaurant))
      {
         if (this.restaurant != null)
         {
            Restaurant oldRestaurant = this.restaurant;
            this.restaurant = null;
            oldRestaurant.removeEmployees(this);
         }
         if (newRestaurant != null)
         {
            this.restaurant = newRestaurant;
            this.restaurant.addEmployees(this);
         }
      }
   }
	*/
}