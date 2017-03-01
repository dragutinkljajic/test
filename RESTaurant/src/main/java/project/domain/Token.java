package project.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Token {

	private static final int EXPIRATION = 60 * 24;
	 
    @Id
    @GeneratedValue
    @Column(name = "tok_id", nullable = false)
    private Long id;
    
    @Column(name = "tok_token", nullable = false)
    private String token; 
    
    @Column(name = "tok_exp", nullable = false)
    private Date expiryDate;
    
    @Column(name = "tok_email", nullable = false)
    private String email;
    
    @Column(name = "tok_password", nullable = false)
    private String password;
    
    @Column(name = "tok_name", nullable = true)
    private String name;
    
    @Column(name = "tok_surname", nullable = true)
    private String surname;
    
    public Token() {}
    
    public Token(String token, String email, String password, String name, String surname) {
    	this.token = token;
    	this.email = email;
    	this.password = password;
    	this.name = name;
    	this.surname = surname;
    }
    
    
    public Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, EXPIRATION);
        return new Date(cal.getTime().getTime());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
