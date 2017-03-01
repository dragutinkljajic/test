/***********************************************************************
 * Module:  VisitHistory.java
 * Author:  Bojan
 * Purpose: Defines the Class VisitHistory
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 9661ba21-cef2-446b-8efb-31932d9e2742 */
public class VisitHistory {
   /** @pdOid f37dbb74-5458-4199-9f41-086abeb505bc */
   public long idHistory;
   
   /** @pdRoleInfo migr=no name=Visit assc=visits coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Visit> visits;
   /** @pdRoleInfo migr=no name=Customer assc=history mult=0..1 side=A */
   public Customer customer;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Visit> getVisits() {
      if (visits == null)
         visits = new java.util.ArrayList<Visit>();
      return visits;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorVisits() {
      if (visits == null)
         visits = new java.util.ArrayList<Visit>();
      return visits.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newVisits */
   public void setVisits(java.util.Collection<Visit> newVisits) {
      removeAllVisits();
      for (java.util.Iterator iter = newVisits.iterator(); iter.hasNext();)
         addVisits((Visit)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newVisit */
   public void addVisits(Visit newVisit) {
      if (newVisit == null)
         return;
      if (this.visits == null)
         this.visits = new java.util.ArrayList<Visit>();
      if (!this.visits.contains(newVisit))
      {
         this.visits.add(newVisit);
         newVisit.setVisitHistory(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldVisit */
   public void removeVisits(Visit oldVisit) {
      if (oldVisit == null)
         return;
      if (this.visits != null)
         if (this.visits.contains(oldVisit))
         {
            this.visits.remove(oldVisit);
            oldVisit.setVisitHistory((VisitHistory)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllVisits() {
      if (visits != null)
      {
         Visit oldVisit;
         for (java.util.Iterator iter = getIteratorVisits(); iter.hasNext();)
         {
            oldVisit = (Visit)iter.next();
            iter.remove();
            oldVisit.setVisitHistory((VisitHistory)null);
         }
      }
   }

}