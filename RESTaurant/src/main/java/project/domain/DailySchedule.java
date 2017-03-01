/***********************************************************************
 * Module:  DailySchedule.java
 * Author:  Bojan
 * Purpose: Defines the Class DailySchedule
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 895af16a-dc7e-4284-accc-b4fa16fe2288 */
public class DailySchedule {
   /** @pdOid b6a9ef84-1d45-4594-a8c4-169a490d0f42 */
   public long idDaily;
   /** @pdOid 3717b60a-bfc3-4e38-89e8-443e95a87e19 */
   public java.util.Date date;
   
   /** @pdRoleInfo migr=no name=Shift assc=shifts coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Shift> shifts;
   /** @pdRoleInfo migr=no name=WaiterShift assc=waiterShifts coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<WaiterShift> waiterShifts;
   /** @pdRoleInfo migr=no name=WorkSchedule assc=dnevniRaspored mult=0..1 side=A */
   public WorkSchedule schedule;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Shift> getShifts() {
      if (shifts == null)
         shifts = new java.util.ArrayList<Shift>();
      return shifts;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorShifts() {
      if (shifts == null)
         shifts = new java.util.ArrayList<Shift>();
      return shifts.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newShifts */
   public void setShifts(java.util.Collection<Shift> newShifts) {
      removeAllShifts();
      for (java.util.Iterator iter = newShifts.iterator(); iter.hasNext();)
         addShifts((Shift)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newShift */
   public void addShifts(Shift newShift) {
      if (newShift == null)
         return;
      if (this.shifts == null)
         this.shifts = new java.util.ArrayList<Shift>();
      if (!this.shifts.contains(newShift))
      {
         this.shifts.add(newShift);
    //     newShift.setDailySchedule(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldShift */
   public void removeShifts(Shift oldShift) {
      if (oldShift == null)
         return;
      if (this.shifts != null)
         if (this.shifts.contains(oldShift))
         {
            this.shifts.remove(oldShift);
           // oldShift.setDailySchedule((DailySchedule)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllShifts() {
      if (shifts != null)
      {
         Shift oldShift;
         for (java.util.Iterator iter = getIteratorShifts(); iter.hasNext();)
         {
            oldShift = (Shift)iter.next();
            iter.remove();
           // oldShift.setDailySchedule((DailySchedule)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<WaiterShift> getWaiterShifts() {
      if (waiterShifts == null)
         waiterShifts = new java.util.ArrayList<WaiterShift>();
      return waiterShifts;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorWaiterShifts() {
      if (waiterShifts == null)
         waiterShifts = new java.util.ArrayList<WaiterShift>();
      return waiterShifts.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newWaiterShifts */
   public void setWaiterShifts(java.util.Collection<WaiterShift> newWaiterShifts) {
      removeAllWaiterShifts();
      for (java.util.Iterator iter = newWaiterShifts.iterator(); iter.hasNext();)
         addWaiterShifts((WaiterShift)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newWaiterShift */
   public void addWaiterShifts(WaiterShift newWaiterShift) {
      if (newWaiterShift == null)
         return;
      if (this.waiterShifts == null)
         this.waiterShifts = new java.util.ArrayList<WaiterShift>();
      if (!this.waiterShifts.contains(newWaiterShift))
      {
         this.waiterShifts.add(newWaiterShift);
         newWaiterShift.setDailySchedule(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldWaiterShift */
   public void removeWaiterShifts(WaiterShift oldWaiterShift) {
      if (oldWaiterShift == null)
         return;
      if (this.waiterShifts != null)
         if (this.waiterShifts.contains(oldWaiterShift))
         {
            this.waiterShifts.remove(oldWaiterShift);
            oldWaiterShift.setDailySchedule((DailySchedule)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllWaiterShifts() {
      if (waiterShifts != null)
      {
         WaiterShift oldWaiterShift;
         for (java.util.Iterator iter = getIteratorWaiterShifts(); iter.hasNext();)
         {
            oldWaiterShift = (WaiterShift)iter.next();
            iter.remove();
            oldWaiterShift.setDailySchedule((DailySchedule)null);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public WorkSchedule getSchedule() {
      return schedule;
   }
   

}