/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models;

import shared.models.DTO.TurnTrackerDTO;

/**
 * TurnTracker is a class that store information regarding the index of the
 * player who's turn it currently is. Additionally, it also stores the current
 * status of the turn, and the index of the player who has obtained Longest
 * Road/Largest Army.
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class TurnTracker {

    private int currentTurn;
    private String status;
    private Integer longestRoad;
    private Integer largestArmy;

    public TurnTracker() {

    }

    public TurnTracker(TurnTrackerDTO turnTracker) {
        this.currentTurn = turnTracker.getCurrentTurn();
        this.status = turnTracker.getStatus();
        this.longestRoad = turnTracker.getLongestRoad();
        this.largestArmy = turnTracker.getLargestArmy();
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLongestRoad() {
        return (longestRoad == null)? -1: longestRoad;
    }

    public void setLongestRoad(int longestRoad) {
    	if(longestRoad >= 0 && longestRoad < 4)
    		this.longestRoad = longestRoad;
    }

    public int getLargestArmy() {
        return (largestArmy == null)? -1: largestArmy;
    }

    public void setLargestArmy(int largestArmy) {
    	if(largestArmy >= 0 && largestArmy < 4)
        this.largestArmy = largestArmy;
    }

    public boolean matchesCurrent(int playerIndex) {
        return playerIndex == this.currentTurn;
    }
}
