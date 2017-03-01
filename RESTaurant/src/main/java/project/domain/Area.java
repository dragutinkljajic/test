/***********************************************************************
 * Module:  Area.java
 * Author:  Bojan
 * Purpose: Defines the Class Area
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid a80521aa-104c-441d-9323-bc88ab179d24 */
public class Area {
   /** @pdOid 17e16472-f4a9-4a2f-8f5a-d9bc4ac4bf70 */
   public long idArea;
   /** @pdOid 9c51c9ff-5b21-4a24-8b90-6df547c3e5e6 */
   public java.lang.String label;
   
   /** @pdRoleInfo migr=no name=Table assc=tablesInArea coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<RestTable> tables;
   /** @pdRoleInfo migr=no name=WaiterShift assc=areasInShifts coll=java.util.Collection impl=java.util.ArrayList mult=0..* side=A */
   public java.util.Collection<WaiterShift> waiterShifts;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<RestTable> getTables() {
      if (tables == null)
         tables = new java.util.ArrayList<RestTable>();
      return tables;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorTables() {
      if (tables == null)
         tables = new java.util.ArrayList<RestTable>();
      return tables.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newTables */
   public void setTables(java.util.Collection<RestTable> newTables) {
      removeAllTables();
      for (java.util.Iterator iter = newTables.iterator(); iter.hasNext();)
         addTables((RestTable)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newTable */
   public void addTables(RestTable newTable) {
      if (newTable == null)
         return;
      if (this.tables == null)
         this.tables = new java.util.ArrayList<RestTable>();
      if (!this.tables.contains(newTable))
         this.tables.add(newTable);
   }
   
   /** @pdGenerated default remove
     * @param oldTable */
   public void removeTables(RestTable oldTable) {
      if (oldTable == null)
         return;
      if (this.tables != null)
         if (this.tables.contains(oldTable))
            this.tables.remove(oldTable);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllTables() {
      if (tables != null)
         tables.clear();
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
         newWaiterShift.addAreas(this);      
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
            oldWaiterShift.removeAreas(this);
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
            oldWaiterShift.removeAreas(this);
         }
      }
   }

}