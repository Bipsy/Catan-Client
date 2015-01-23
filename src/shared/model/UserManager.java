/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.util.List;

/** UserManager stores the meta information regarding the users. This includes
 * the updated Turn Tracker and the current list of Users.
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class UserManager {
    private List users;
    TurnTracker turnTracker;
}
