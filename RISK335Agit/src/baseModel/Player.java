/**
 * @author Chris Ray
 * Created on 6:02:03 PM Nov 25, 2011
 *
 */
package baseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * @author Chris Ray Created on 6:02:03 PM Nov 25, 2011
 */
public class Player extends Observable {

	private int numberOfUnits;
	private int numberOfTerritories;
	private int newUnits; // the number of units yet to be placed on the board
							// this turn
	private Team team;
	private ArrayList<TerritoryCard> cards;
	private ArrayList<Territory> territoriesOwned;

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
		territoriesOwned = new ArrayList<Territory>();
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
		return this.getTerritoriesOwned().size();
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
	 * @return the territoriesOwned
	 * @author Chris Ray Created on 4:40:50 PM Nov 30, 2011
	 */
	public ArrayList<Territory> getTerritoriesOwned() {
		return territoriesOwned;
	}

	/**
	 * @return the continentsOwned
	 * @author Chris Ray Created on 2:38:09 PM Dec 4, 2011
	 */
	public ArrayList<Continent> getContinentsOwned() {

		// temporary Map to use for this purpose only
		HashMap<String, Continent> continentsPossible = new Map().getMap();
		ArrayList<Continent> continentsOwned = new ArrayList<Continent>();
		continentsOwned.add(continentsPossible.get("North America"));
		continentsOwned.add(continentsPossible.get("South America"));
		continentsOwned.add(continentsPossible.get("Africa"));
		continentsOwned.add(continentsPossible.get("Australia"));
		continentsOwned.add(continentsPossible.get("Europe"));
		continentsOwned.add(continentsPossible.get("Asia"));

		for (Territory ter : this.getTerritoriesOwned())
			if (!continentsPossible.get("North America").getChildren()
					.contains(ter))
				continentsOwned.remove(continentsPossible.get("North America"));
			else if (!continentsPossible.get("South America").getChildren()
					.contains(ter))
				continentsOwned.remove(continentsPossible.get("South America"));
			else if (!continentsPossible.get("Africa").getChildren()
					.contains(ter))
				continentsOwned.remove(continentsPossible.get("Africa"));
			else if (!continentsPossible.get("Australia").getChildren()
					.contains(ter))
				continentsOwned.remove(continentsPossible.get("Australia"));
			else if (!continentsPossible.get("Europe").getChildren()
					.contains(ter))
				continentsOwned.remove(continentsPossible.get("Europe"));
			else if (!continentsPossible.get("Asia").getChildren()
					.contains(ter))
				continentsOwned.remove(continentsPossible.get("Asia"));
		return continentsOwned;
	}

}
