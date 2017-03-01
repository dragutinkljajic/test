/***********************************************************************
 * Module:  Online.java
 * Author:  Bojan
 * Purpose: Defines the Class Online
 ***********************************************************************/
package project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Online {

	@Id
	@GeneratedValue
	@Column(name = "onl_id", nullable = false)
    private long id;

	@OneToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "usr_id", unique = true)
	@JoinColumn(name = "user_id")
    private User user;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}