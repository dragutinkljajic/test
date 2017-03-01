/***********************************************************************
 * Module:  WaiterShift.java
 * Author:  Bojan
 * Purpose: Defines the Class WaiterShift
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid c778dd90-fd9a-41ac-b9fc-ffb7fd7f6161 */
public class WaiterShift {
   /** @pdOid 8470047f-af3a-468c-9fe4-b9021b26bcc4 */
   public long idWShift;
   /** @pdOid ce88332e-67f5-486e-b4a4-53a4dd202e5a */
   public java.util.Date sBeginning;
   /** @pdOid 3b28de64-e992-4e8a-827c-5d2202764857 */
   public java.util.Date sEnd;
   
   public java.util.Collection waiterArea;
   /** @pdRoleInfo migr=no name=Area assc=areasInShifts coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Area> areas;
   /** @pdRoleInfo migr=no name=DailySchedule assc=waiterShifts mult=0..1 side=A */
   public DailySchedule dailySchedule;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Area> getAreas() {
      if (areas == null)
         areas = new java.util.ArrayList<Area>();
      return areas;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAreas() {
      if (areas == null)
         areas = new java.util.ArrayList<Area>();
      return areas.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAreas */
   public void setAreas(java.util.Collection<Area> newAreas) {
      removeAllAreas();
      for (java.util.Iterator iter = newAreas.iterator(); iter.hasNext();)
         addAreas((Area)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newArea */
   public void addAreas(Area newArea) {
      if (newArea == null)
         return;
      if (this.areas == null)
         this.areas = new java.util.ArrayList<Area>();
      if (!this.areas.contains(newArea))
      {
         this.areas.add(newArea);
         newArea.addWaiterShifts(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldArea */
   public void removeAreas(Area oldArea) {
      if (oldArea == null)
         return;
      if (this.areas != null)
         if (this.areas.contains(oldArea))
         {
            this.areas.remove(oldArea);
            oldArea.removeWaiterShifts(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAreas() {
      if (areas != null)
      {
         Area oldArea;
         for (java.util.Iterator iter = getIteratorAreas(); iter.hasNext();)
         {
            oldArea = (Area)iter.next();
            iter.remove();
            oldArea.removeWaiterShifts(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public DailySchedule getDailySchedule() {
      return dailySchedule;
   }
   
   /** @pdGenerated default parent setter
     * @param newDailySchedule */
   public void setDailySchedule(DailySchedule newDailySchedule) {
      if (this.dailySchedule == null || !this.dailySchedule.equals(newDailySchedule))
      {
         if (this.dailySchedule != null)
         {
            DailySchedule oldDailySchedule = this.dailySchedule;
            this.dailySchedule = null;
            oldDailySchedule.removeWaiterShifts(this);
         }
         if (newDailySchedule != null)
         {
            this.dailySchedule = newDailySchedule;
            this.dailySchedule.addWaiterShifts(this);
         }
      }
   }

}