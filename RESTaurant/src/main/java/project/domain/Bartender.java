package project.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bartender extends Employee {

	private static final long serialVersionUID = -4567146652809235714L;
	
	@Column(name = "bar_cocktails", nullable = false)
    private boolean cocktails = false;

	protected Bartender() {}
	
	@OneToMany(mappedBy="bartender")
	@JsonIgnore
   	private List<DrinkOrder> drinkOrder;
	
	public boolean isCocktails() {
		return cocktails;
	}

	public void setCocktails(boolean cocktails) {
		this.cocktails = cocktails;
	}

	public List<DrinkOrder> getDrinkOrder() {
		return drinkOrder;
	}

	public void setDrinkOrder(List<DrinkOrder> drinkOrder) {
		this.drinkOrder = drinkOrder;
	}
	
	public void addRating(int rating){
		
	}
   	
   /*
    * 
   public java.util.Collection<BartenderRating> ratings;

   public java.util.Collection<Shift> shifts;
   */
   
	
  
}