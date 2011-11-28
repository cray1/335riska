/**
 * @author Chris Ray
 * Created on 6:02:03 PM Nov 25, 2011
 *
 */
package baseModel;

import java.util.ArrayList;

/**
 * @author Chris Ray Created on 6:02:03 PM Nov 25, 2011
 */
public class Player {

	private int numberOfUnits;
	private int numberOfTerritories;
	private int newUnits; // the number of units yet to be placed on the board
							// this turn
	private Team team;
	private ArrayList<TerritoryCard> cards;

	/**
	 * @param t
	 * @author Chris Ray Created on 6:28:28 PM Nov 25, 2011
	 * 
	 */
	public Player(Team t) {
		team = t;
		numberOfUnits = 0;
		numberOfTerritories = 0;
		newUnits = 0;
		cards = new ArrayList<TerritoryCard>();
	}

	/**
	 * @return the numberOfUnits
	 * @author Chris Ray Created on 6:20:50 PM Nov 25, 2011
	 */
	public int getNumberOfUnits() {
		return numberOfUnits;
	}

	/**
	 * @return the numberOfTerritories
	 * @author Chris Ray Created on 6:21:00 PM Nov 25, 2011
	 */
	public int getNumberOfTerritories() {
		return numberOfTerritories;
	}

	/**
	 * @return the newUnits
	 * @author Chris Ray Created on 6:21:12 PM Nov 25, 2011
	 */
	public int getNewUnits() {
		return newUnits;
	}

	/**
	 * @return the newUnits
	 * @author Chris Ray Created on 6:21:12 PM Nov 25, 2011
	 * @param newUnits
	 */
	public void setNewUnits(int newUnits) {
		this.newUnits = newUnits;
	}

	/**
	 * @return the team
	 * @author Chris Ray Created on 6:23:29 PM Nov 25, 2011
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @return the cards
	 * @author Chris Ray Created on 6:23:50 PM Nov 25, 2011
	 */
	public ArrayList<TerritoryCard> getCards() {
		return cards;
	}

	/**
	 * @author Chris Ray Created on 5:20:09 PM Nov 27, 2011
	 * 
	 */
	public void setNumberOfTerritories(int num) {
		numberOfTerritories = num;
	}
}
