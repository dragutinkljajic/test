package project.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Supplier extends User {

	private static final long serialVersionUID = -8677490291747527413L;

	@Column(name = "sup_label", nullable = false)
    private String label;

	@Column(name = "sup_desc", nullable =  true)
    private String description;

	@Column(name = "sup_pass_changed", nullable = false)
    private boolean passChanged = false;
	
	@OneToMany(mappedBy="supplier")
	@JsonIgnore
	private Set<Offer> offers;
	
	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public Supplier() {}
	
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

	public boolean isPassChanged() {
		return passChanged;
	}

	public void setPassChanged(boolean passChanged) {
		this.passChanged = passChanged;
	}
	
	
   /*
   public java.util.Collection<Offer> offers;

   public java.util.Collection<RestorauntManager> suppliers;
   
   
   public java.util.Collection<Offer> getOffers() {
      if (offers == null)
         offers = new java.util.ArrayList<Offer>();
      return offers;
   }
   
   public java.util.Iterator getIteratorOffers() {
      if (offers == null)
         offers = new java.util.ArrayList<Offer>();
      return offers.iterator();
   }

   public void setOffers(java.util.Collection<Offer> newOffers) {
      removeAllOffers();
      for (java.util.Iterator iter = newOffers.iterator(); iter.hasNext();)
         addOffers((Offer)iter.next());
   }
   
   public void addOffers(Offer newOffer) {
      if (newOffer == null)
         return;
      if (this.offers == null)
         this.offers = new java.util.ArrayList<Offer>();
      if (!this.offers.contains(newOffer))
         this.offers.add(newOffer);
   }
   
   public void removeOffers(Offer oldOffer) {
      if (oldOffer == null)
         return;
      if (this.offers != null)
         if (this.offers.contains(oldOffer))
            this.offers.remove(oldOffer);
   }
   
   public void removeAllOffers() {
      if (offers != null)
         offers.clear();
   }

   public java.util.Collection<RestorauntManager> getSuppliers() {
      if (suppliers == null)
         suppliers = new java.util.ArrayList<RestorauntManager>();
      return suppliers;
   }

   public java.util.Iterator getIteratorSuppliers() {
      if (suppliers == null)
         suppliers = new java.util.ArrayList<RestorauntManager>();
      return suppliers.iterator();
   }
   
   public void setSuppliers(java.util.Collection<RestorauntManager> newSuppliers) {
      removeAllSuppliers();
      for (java.util.Iterator iter = newSuppliers.iterator(); iter.hasNext();)
         addSuppliers((RestorauntManager)iter.next());
   }
   
   public void addSuppliers(RestorauntManager newRestorauntManager) {
      if (newRestorauntManager == null)
         return;
      if (this.suppliers == null)
         this.suppliers = new java.util.ArrayList<RestorauntManager>();
      if (!this.suppliers.contains(newRestorauntManager))
      {
         this.suppliers.add(newRestorauntManager);
         //newRestorauntManager.addManagers(this);      
      }
   }
   
   public void removeSuppliers(RestorauntManager oldRestorauntManager) {
      if (oldRestorauntManager == null)
         return;
      if (this.suppliers != null)
         if (this.suppliers.contains(oldRestorauntManager))
         {
            this.suppliers.remove(oldRestorauntManager);
            //oldRestorauntManager.removeManagers(this);
         }
   }

   public void removeAllSuppliers() {
      if (suppliers != null)
      {
         RestorauntManager oldRestorauntManager;
         for (java.util.Iterator iter = getIteratorSuppliers(); iter.hasNext();)
         {
            oldRestorauntManager = (RestorauntManager)iter.next();
            iter.remove();
            //oldRestorauntManager.removeManagers(this);
         }
      }
   }
	*/
}