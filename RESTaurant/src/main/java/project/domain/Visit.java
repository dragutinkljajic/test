/***********************************************************************
 * Module:  Visit.java
 * Author:  Bojan
 * Purpose: Defines the Class Visit
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 9157b952-3450-4a01-a635-a6c2b0c4eff6 */
public class Visit {
   /** @pdOid fa40c633-8a4b-4875-b2c7-8005d508b7ee */
   public long idVisit;
   
   /** @pdRoleInfo migr=no name=Rating assc=rating mult=0..1 */
 //  public Rating rating;
   /** @pdRoleInfo migr=no name=VisitHistory assc=visits mult=0..1 side=A */
   public VisitHistory visitHistory;
   /** @pdRoleInfo migr=no name=Reservation assc=reserved mult=0..1 side=A */
   public Reservation reservation;
   /** @pdRoleInfo migr=no name=Invite assc=visited mult=0..1 side=A */
   public Invite invite;
   
   
   /** @pdGenerated default parent getter */
   public VisitHistory getVisitHistory() {
      return visitHistory;
   }
   
   /** @pdGenerated default parent setter
     * @param newVisitHistory */
   public void setVisitHistory(VisitHistory newVisitHistory) {
      if (this.visitHistory == null || !this.visitHistory.equals(newVisitHistory))
      {
         if (this.visitHistory != null)
         {
            VisitHistory oldVisitHistory = this.visitHistory;
            this.visitHistory = null;
            oldVisitHistory.removeVisits(this);
         }
         if (newVisitHistory != null)
         {
            this.visitHistory = newVisitHistory;
            this.visitHistory.addVisits(this);
         }
      }
   }

}