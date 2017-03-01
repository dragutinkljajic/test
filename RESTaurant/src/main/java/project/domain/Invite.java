/***********************************************************************
 * Module:  Invite.java
 * Author:  Bojan
 * Purpose: Defines the Class Invite
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid e1ec0ac5-e7f7-4d9d-bbef-5777bf9abc30 */
public class Invite {
   /** @pdOid b9d6cb5c-d706-4291-9a88-8541850fcdf2 */
   public long idInvite;
   /** @pdOid 79483e02-d591-4e9d-894a-cd1f1a82aa86 */
   public java.lang.String status;
   
   /** @pdRoleInfo migr=no name=Reservation assc=invite mult=0..1 side=A */
   public Reservation reservation;
   
   
   /** @pdGenerated default parent getter */
   public Reservation getReservation() {
      return reservation;
   }
   
}