package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reservation {

	@Id
	@GeneratedValue
	@Column(name = "res_id", nullable = false)
    private Long id;

	//datum samo, recimo sa 00:00 vremenom, pocetak dana
	@Column(name = "res_date", nullable = false)
    private Date date;
	
	//isti taj datum, samo sa drugacijim vremenom... posto valjda date cuva u
	//bazi datum i vreme, ovako mi je generisao...
	//ako mislis da valja promeniti, ti promeni hehe
	@Column(name = "res_time", nullable = false)
    private Date time;
	
	//trajanje izrazeno u satima
	@Column(name = "res_duration", nullable = false)
    private short duration;
	
	@Column(name = "res_status", nullable = false)
	private String status;
	
	//stolovi restorana u kojem je izvrsena rezervacija
	//moze da ti posluzi da skontas koji konobar/konobari treba da bude zaduzen/i za porudzbine
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="ReservedTables",
	 joinColumns=@JoinColumn(name="res_id"),
	 inverseJoinColumns=@JoinColumn(name="table_id"))
	@JsonIgnore
    private List<RestTable> tables;

    //pozivnice prijatelja, ugl. za tebe nebitno najverovatnije
    //private List<Invite> invite;

    //porudzbine, mogu da se formiraju pri rezervisanju, mogu i naknadno da se dodaju
    //u toku rezervacije
	@OneToMany(mappedBy="reservation")
	@JsonIgnore
    private List<RestOrder> order;

    //lik koji je rezervisao inicijalno
    @ManyToOne(optional=false)
	@JoinColumn(name="res_customer",  referencedColumnName="usr_id")
    private Customer customer;
   
    @ManyToOne(optional=false)
	@JoinColumn(name="res_restaurant",  referencedColumnName="rst_id")
    private Restaurant restaurant;
    
    public Reservation() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public short getDuration() {
		return duration;
	}

	public void setDuration(short duration) {
		this.duration = duration;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<RestTable> getTables() {
		return tables;
	}

	public void setTables(List<RestTable> tables) {
		this.tables = tables;
	}

	public List<RestOrder> getOrder() {
		return order;
	}

	public void setOrder(List<RestOrder> order) {
		this.order = order;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
    
    
}