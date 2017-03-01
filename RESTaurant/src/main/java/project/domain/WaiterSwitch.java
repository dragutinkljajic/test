/***********************************************************************
 * Module:  WaiterSwitch.java
 * Author:  Bojan
 * Purpose: Defines the Class WaiterSwitch
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid b615fd67-95c9-4196-8f0b-6c12716ceb52 */
public class WaiterSwitch {
   /** @pdOid 5ee4c265-8ba4-4e0b-a920-0db5e7f7f72b */
   public long idSwitch;
   /** @pdOid 0a00c64c-b7a8-4c61-84b7-93eca2676779 */
   public java.util.Date switchTime;
   
   /** @pdRoleInfo migr=no name=Order assc=orderSwitch mult=0..1 */
   public RestOrder orders;
   /** @pdRoleInfo migr=no name=Waiter assc=taken mult=0..1 side=A */
   public Waiter taken;
   /** @pdRoleInfo migr=no name=Waiter assc=given mult=0..1 side=A */
   public Waiter given;

}