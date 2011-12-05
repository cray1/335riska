/**
 * @author Chris Ray
 * Created on 11:41:14 AM Dec 4, 2011
 *
 */
package baseModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Chris Ray Created on 11:41:14 AM Dec 4, 2011
 */
public class PlayerAI extends Player implements Observer {
	private Game game;
	private boolean turnOver;
	public boolean initialUnitsPlaced;
	private boolean allContinentsHaveBeenClaimed;

	/**
	 * @param t
	 * @author Chris Ray Created on 11:41:27 AM Dec 4, 2011
	 * 
	 */
	public PlayerAI(Team t) {
		super(t);
		turnOver = true;
		game = new Game();
		initialUnitsPlaced = false;
		// TODO Auto-generated constructor stub

	}

	/**
	 * Call this when it is the AI's turn....do not call awardBeginningUnits in
	 * game...ai takes care of that.
	 * 
	 * @author Chris Ray Created on 9:38:53 PM Dec 4, 2011
	 * 
	 */
	public void startTurn() {
		game.setActivePlayer(this);
		game.awardBeginningUnits();
		placeInitialUnits();
		chooseCardsTurnIn();
		setPlacementPriority();
		chooseUnitPlacement();
		chooseAttack();
		chooseMove();
		endTurn();
	}

	/**
	 * 
	 * @author Chris Ray Created on 4:59:20 PM Dec 4, 2011
	 * 
	 */
	private void placeInitialUnits() {
		if (!initialUnitsPlaced) {
			boolean turnDone = false;
			int previousNumOfTerritoriesOwned = this.getNumberOfTerritories();
			if (!turnDone) {

				ArrayList<Continent> continents = new ArrayList<Continent>();
				continents.addAll(game.getMap().getMap().values());

				for (Continent con : continents) {
					if (turnDone)
						break;
					if (!con.isClaimed() || !con.hasEnemy(this.getTeam()))
						for (Territory ter : con.getChildren()) {
							game.claimTerritory(ter);
							if (previousNumOfTerritoriesOwned < this
									.getNumberOfTerritories()) {
								turnDone = true;
								break;
							}
						}
					for (Territory ter : con.getChildren())
						if (!this.getTerritoriesOwned().contains(ter)
								&& (ter.getOwningTeam() == null)) {
							game.claimTerritory(ter);
							if (previousNumOfTerritoriesOwned < this
									.getNumberOfTerritories()) {
								turnDone = true;
								break;
							}
						}
				}

			}
		}
	}

	/**
	 * @author Chris Ray Created on 11:52:53 AM Dec 4, 2011
	 * 
	 */
	private void chooseCardsTurnIn() {
		// TODO Auto-generated method stub
		if (this.getCards().size() >= 3) {
			// enumerate through every possible combination of cards until a
			// valid one is found
			boolean endTurnIn = false;
			Iterator<TerritoryCard> cardsItr1 = this.getCards().iterator();
			while (cardsItr1.hasNext() && !endTurnIn) {
				TerritoryCard card1 = cardsItr1.next();
				Iterator<TerritoryCard> cardsItr2 = this.getCards().iterator();
				while (cardsItr2.hasNext() && !endTurnIn) {
					TerritoryCard card2 = cardsItr2.next();
					Iterator<TerritoryCard> cardsItr3 = this.getCards()
							.iterator();
					while (cardsItr3.hasNext() && !endTurnIn) {
						TerritoryCard card3 = cardsItr3.next();
						// make sure no cards are the same instance
						if (!(card1.getGuid().equals(card2.getGuid())
								|| card1.getGuid().equals(card3.getGuid()) || card2
								.getGuid().equals(card3.getGuid())))
							if (game.turnInCards(card1, card2, card3)) {
								// we found a valid card combo
								this.notifyObservers(game.getMap());
								endTurnIn = true;
							}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @author Chris Ray Created on 2:15:04 PM Dec 4, 2011
	 * 
	 */
	private void setPlacementPriority() {
		for (Territory ter : this.getTerritoriesOwned())
			ter.setPriority(0);
		for (Territory ter : this.getTerritoriesOwned()) {
			// add higher priority if on the edges of any other continents
			if (ter.toString() == "Alaska")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Greenland")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Venezuela")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Brazil")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Iceland")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Western Europe")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Southern Europe")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Ukraine")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "North Africa")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Egypt")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "East Africa")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Ural")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Afghanistan")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Middle East")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Kamchatka")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Siam")
				ter.setPriority(ter.getPriority() + 1);
			else if (ter.toString() == "Indonesia")
				ter.setPriority(ter.getPriority() + 1);

			// raise priority if ter is part of the continents owned by this AI
			if (this.getContinentsOwned().contains(ter)) {
				ter.setPriority(ter.getPriority() + 1);
				if (ter.getParentContinent() == "Asia")
					ter.setPriority(ter.getPriority() + 1);
				else if (ter.getParentContinent() == "Europe")
					ter.setPriority(ter.getPriority() + 1);
				else if (ter.getParentContinent() == "North America")
					ter.setPriority(ter.getPriority() + 1);
				else if (ter.getParentContinent() == "Africa")
					ter.setPriority(ter.getPriority() + 1);
				else if (ter.getParentContinent() == "South America")
					ter.setPriority(ter.getPriority() + 1);
			}
			// raise priority if there are big enemy armies next to territory
			for (Territory neighbor : ter.getNeighbors())
				if ((neighbor.getOwningTeam() != this.getTeam())
						&& (neighbor.getUnitsOnTerritory() >= ter
								.getUnitsOnTerritory())
						&& (neighbor.getUnitsOnTerritory() >= 3))
					ter.setPriority(ter.getPriority() + 1);

		}
	}

	/**
	 * @author Chris Ray Created on 11:52:56 AM Dec 4, 2011
	 * 
	 */
	private void chooseUnitPlacement() {
		if (this.getNewUnits() >= 1) {
			// highestPriority
			ArrayList<Territory> sorted3 = new ArrayList<Territory>();
			// mediumPriority
			ArrayList<Territory> sorted2 = new ArrayList<Territory>();
			// normalPriority
			ArrayList<Territory> sorted1 = new ArrayList<Territory>();
			// lowPriority
			ArrayList<Territory> sorted0 = new ArrayList<Territory>();

			// add territories to highestPriority
			for (Territory ter : this.getTerritoriesOwned())
				if (ter.getPriority() >= 3)
					sorted3.add(ter);
			// add territories to mediumPriority
			for (Territory ter : this.getTerritoriesOwned())
				if (ter.getPriority() == 2)
					sorted2.add(ter);
			// add territories to normalPriority
			for (Territory ter : this.getTerritoriesOwned())
				if (ter.getPriority() == 1)
					sorted1.add(ter);
			// add territories to lowPriority
			for (Territory ter : this.getTerritoriesOwned())
				if (ter.getPriority() == 0)
					sorted0.add(ter);

			// go through placing units getNewUnits number of times
			for (int i = 0; i < this.getNewUnits(); i++) {

				// limit the amount of units to be placed in a particular
				// priority level
				int unitAmountLimiter = 10;
				while (unitAmountLimiter >= 0)

					// break if no more units to add or no territories in the
					// sorted array list
					if ((this.getNewUnits() == 0) || (sorted3.size() <= 0))
						break;

				// place one unit at a time in each territory in the sorted
				// sublist
				for (Territory ter : sorted3)
					if (this.getNewUnits() >= 1) {
						ter.addUnits(1, this.getTeam());
						this.setNewUnits(this.getNewUnits() - 1);
						unitAmountLimiter--;
					} else
						break;

				// limit the amount of units to be placed in a particular
				// priority level
				unitAmountLimiter = 10;
				while (unitAmountLimiter >= 0)

					// break if no more units to add or no territories in the
					// sorted array list
					if ((this.getNewUnits() == 0) || (sorted2.size() <= 0))
						break;

				// place one unit at a time in each territory in the sorted
				// sublist
				for (Territory ter : sorted2)
					if (this.getNewUnits() >= 1) {
						ter.addUnits(1, this.getTeam());
						this.setNewUnits(this.getNewUnits() - 1);
						unitAmountLimiter--;
					} else
						break;

				// limit the amount of units to be placed in a particular
				// priority level
				unitAmountLimiter = 10;
				while (unitAmountLimiter >= 0)

					// break if no more units to add or no territories in the
					// sorted array list
					if ((this.getNewUnits() == 0) || (sorted1.size() <= 0))
						break;

				// place one unit at a time in each territory in the sorted
				// sublist
				for (Territory ter : sorted1)
					if (this.getNewUnits() >= 1) {
						ter.addUnits(1, this.getTeam());
						this.setNewUnits(this.getNewUnits() - 1);
						unitAmountLimiter--;
					} else
						break;

				// limit the amount of units to be placed in a particular
				// priority level
				unitAmountLimiter = 10;
				while (unitAmountLimiter >= 0)

					// break if no more units to add or no territories in the
					// sorted array list
					if ((this.getNewUnits() == 0) || (sorted0.size() <= 0))
						break;

				// place one unit at a time in each territory in the sorted
				// sublist
				for (Territory ter : sorted0)
					if (this.getNewUnits() >= 1) {
						ter.addUnits(1, this.getTeam());
						this.setNewUnits(this.getNewUnits() - 1);
						unitAmountLimiter--;
					} else
						break;

			}
			// once done placing units set all priority to 0

		}
	}

	/**
	 * @author Chris Ray Created on 11:52:58 AM Dec 4, 2011
	 * 
	 */
	private void chooseAttack() {
		int numOfTersToAttack = 3;
		for (Territory ter : this.getTerritoriesOwned()) {
			// priority 3
			for (Territory neighbor : ter.getNeighbors())
				if ((neighbor.getOwningTeam() != this.getTeam())
						&& (numOfTersToAttack > 0))
					// enemy territory
					if ((ter.getPriority() >= 3)
							&& (ter.getUnitsOnTerritory() >= 4)) {
						// attack until cannot attack anymore
						// try with 3 die
						while (game.attackTerritory(ter, neighbor, 3))
							;
						// try with 2 die
						while (game.attackTerritory(ter, neighbor, 2))
							;
						// try with 1 die
						while (game.attackTerritory(ter, neighbor, 1))
							;
						if ((neighbor.getUnitsOnTerritory() <= 0)
								|| (ter.getUnitsOnTerritory() <= 0))
							numOfTersToAttack--;

					}
			// priority 2
			for (Territory neighbor : ter.getNeighbors())
				if ((neighbor.getOwningTeam() != this.getTeam())
						&& (numOfTersToAttack > 0))
					// enemy territory
					if ((ter.getPriority() == 2)
							&& (ter.getUnitsOnTerritory() >= 4)) {
						// attack until cannot attack anymore
						// try with 3 die
						while (game.attackTerritory(ter, neighbor, 3))
							;
						// try with 2 die
						while (game.attackTerritory(ter, neighbor, 2))
							;
						// try with 1 die
						while (game.attackTerritory(ter, neighbor, 1))
							;
						if ((neighbor.getUnitsOnTerritory() <= 0)
								|| (ter.getUnitsOnTerritory() <= 0))
							numOfTersToAttack--;

					}
			// priority 1
			for (Territory neighbor : ter.getNeighbors())
				if ((neighbor.getOwningTeam() != this.getTeam())
						&& (numOfTersToAttack > 0))
					// enemy territory
					if ((ter.getPriority() == 1)
							&& (ter.getUnitsOnTerritory() >= 4)) {
						// attack until cannot attack anymore
						// try with 3 die
						while (game.attackTerritory(ter, neighbor, 3))
							;
						// try with 2 die
						while (game.attackTerritory(ter, neighbor, 2))
							;
						// try with 1 die
						while (game.attackTerritory(ter, neighbor, 1))
							;
						if ((neighbor.getUnitsOnTerritory() <= 0)
								|| (ter.getUnitsOnTerritory() <= 0))
							numOfTersToAttack--;

					}
		}
		// priority 0 turtle

	}

	/**
	 * @author Chris Ray Created on 11:53:07 AM Dec 4, 2011
	 * 
	 */
	private void chooseMove() {
		// refresh priority
		this.setPlacementPriority();

		boolean doneMove = false;
		for (Territory ter : this.getTerritoriesOwned()) {
			if (doneMove == true)
				break;
			// only move from lower priority to higher priority
			if (ter.getPriority() >= 2)
				for (Territory neighbor : ter.getNeighbors())
					if ((neighbor.getOwningTeam() == this.getTeam())
							&& (neighbor.getPriority() >= 3)) {
						game.move(ter, neighbor, ter.getUnitsOnTerritory() - 1);
						doneMove = true;
						break;
					}
			// only move from lower priority to higher priority
			if (ter.getPriority() == 1)
				for (Territory neighbor : ter.getNeighbors())
					if ((neighbor.getOwningTeam() == this.getTeam())
							&& (neighbor.getPriority() >= 2)) {
						game.move(ter, neighbor, ter.getUnitsOnTerritory() - 1);
						doneMove = true;
						break;
					}
			// only move from lower priority to higher priority
			if (ter.getPriority() == 0)
				for (Territory neighbor : ter.getNeighbors())
					if ((neighbor.getOwningTeam() == this.getTeam())
							&& (neighbor.getPriority() >= 1)) {
						game.move(ter, neighbor, ter.getUnitsOnTerritory() - 1);
						doneMove = true;
						break;
					}
		}
	}

	/**
	 * @author Chris Ray Created on 11:53:10 AM Dec 4, 2011
	 * 
	 */
	private void endTurn() {
		// TODO Auto-generated method stub
		this.notifyObservers(game.getMap());
		this.notifyObservers(this.turnOver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * 
	 * @author Chris Ray Created on 12:53:51 PM Dec 4, 2011
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Game)
			game = (Game) arg;
	}
}
