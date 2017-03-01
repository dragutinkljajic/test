package project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SystemManager extends User {

	private static final long serialVersionUID = -9016406638727440790L;
	
	@Column(name = "sys_name", nullable = true)
	private String name;
	
	@Column(name = "sys_surname", nullable = true)
	private String surname;

	public SystemManager() {}
	
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

	
	
}