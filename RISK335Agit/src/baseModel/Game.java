/**
 * @author Chris Ray
 * Created on 8:15:11 PM Nov 26, 2011
 *
 */
package baseModel;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Chris Ray Created on 8:15:11 PM Nov 26, 2011
 */
public class Game extends CommandInterface implements Observer {
	private CardDeck deck;

	private int unitMultiplier;
	private Map map;
	private Boolean firstTerritory;
	private Move move;
	private LinkedList<Player> players;
	private ArrayList<Die> defendDice;
	private ArrayList<Die> attackDice;
	private Player activePlayer;
	private Iterator<Player> activeItr;
	public boolean killAI = false;

	/**
	 * @author Chris Ray Created on 8:57:30 AM Dec 2, 2011
	 * @return
	 * 
	 */
	public boolean startGame() {

		if (checkSetUp()) {
			activePlayer = players.getFirst();
			while (!killAI)
				if (activePlayer instanceof PlayerAI) {
					this.notifyObservers(this);
					((PlayerAI) activePlayer).setGame(this);
					((PlayerAI) activePlayer).startTurn();
				}
			return true;

		} else
			return false;

	}

	/**
	 * @return the deck
	 * @author Chris Ray Created on 9:50:43 PM Dec 4, 2011
	 */
	public CardDeck getDeck() {
		return deck;
	}

	private boolean checkSetUp() {
		if (players.size() >= 2)
			return true;
		return false;
	}

	/**
	 * 
	 * @author Chris Ray Created on 8:16:57 PM Nov 26, 2011
	 * 
	 */
	public Game() {
		deck = new CardDeck();
		unitMultiplier = 4;
		setMap(new Map());
		firstTerritory = true;
		move = new Move();
		setPlayers(new LinkedList<Player>());
		// activeItr = players.iterator();
		// defend Dice
		defendDice = new ArrayList<Die>();
		defendDice.add(new Die());
		defendDice.add(new Die());
		// attack Dice
		attackDice = new ArrayList<Die>();
		attackDice.add(new Die());
		attackDice.add(new Die());
		attackDice.add(new Die());
		// setActivePlayer(activeItr.next()); // placeholder

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see baseModel.CommandInterface#drawCard(baseModel.Player)
	 * 
	 * @author Chris Ray Created on 8:27:35 PM Nov 26, 2011
	 */
	@Override
	public boolean drawCard() {
		try {
			activePlayer.getCards().add(deck.drawCard());
			this.notifyObservers(activePlayer);
			this.notifyObservers(deck);
			return true;
		} catch (Exception e) {
			System.out.println("Draw card was unsuccessful");
			System.out.println(e.getMessage());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see baseModel.CommandInterface#turnInCards(baseModel.Player)
	 * 
	 * @author Chris Ray Created on 8:27:35 PM Nov 26, 2011
	 */
	@Override
	public boolean turnInCards(TerritoryCard card1, TerritoryCard card2,
			TerritoryCard card3) {
		if (isValidTurnin(card1, card2, card3)) {
			activePlayer.getCards().remove(card1);
			activePlayer.getCards().remove(card2);
			activePlayer.getCards().remove(card3);

			// Check to see if any of the cards territories are owned by the
			// player
			// if so, award the player two more units
			if (activePlayer.getTerritoriesOwned().contains(
					card1.getCardTerritory())
					|| activePlayer.getTerritoriesOwned().contains(
							card2.getCardTerritory())
					|| activePlayer.getTerritoriesOwned().contains(
							card3.getCardTerritory()))
				// Special award: award 2 units (and only two units(no more
				// extra units if more than one card matches)):
				activePlayer.setNewUnits(activePlayer.getNewUnits() + 2);

			// ...
			// /removed code: check commit message for code removed
			// ...

			// Normal award: award unitMultiplier units to player
			activePlayer.setNewUnits(activePlayer.getNewUnits()
					+ unitMultiplier);

			// move the unitMultiplier to the next position
			unitMultiplierUp();
			this.notifyObservers(activePlayer);
			return true;
		} else
			return false;
	}

	private boolean isValidTurnin(TerritoryCard card1, TerritoryCard card2,
			TerritoryCard card3) {
		// 2 and a wildcard or 1 and 2 wildcards
		ArrayList<TerritoryCard> cards = new ArrayList<TerritoryCard>();
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		if (checkContains(cards, CardType.WILDCARD))
			return true;
		// 3 of a kind
		else if ((card1.getCardType() == card2.getCardType())
				&& (card2.getCardType() == card3.getCardType()))
			return true;
		// 1 of each
		else if (checkContains(cards, CardType.CANNON)
				&& checkContains(cards, CardType.SOLDIER)
				&& checkContains(cards, CardType.HORSE))
			return true;
		else
			return false;
	}

	/**
	 * Checks to see if an ArrayList<TerritoryCard> contains a certain cardType
	 * 
	 * @author Chris Ray Created on 4:30:55 PM Dec 5, 2011
	 * 
	 */
	private boolean checkContains(ArrayList<TerritoryCard> cards, CardType ct) {
		for (TerritoryCard card : cards)
			if (card.getCardType().equals(ct))
				return true;
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see baseModel.CommandInterface#placeUnitOnTerritory(baseModel.Player,
	 * java.lang.String, baseModel.Unit)
	 * 
	 * @author Chris Ray Created on 8:27:35 PM Nov 26, 2011
	 */
	@Override
	public boolean claimTerritory(Territory territory) {
		if (territory.getUnitsOnTerritory() == 0) {
			boolean work = territory.addUnits(1, activePlayer.getTeam());
			territory.setOwningTeam(activePlayer.getTeam());
			territory.setOwningPlayer(activePlayer);

			activePlayer.getTerritoriesOwned().add(territory);
			this.notifyObservers(activePlayer);
			this.notifyObservers(territory);
			this.notifyObservers(map);

			this.notifyObservers(this);// only useful for AI
			return work;
		}
		return false;
	}

	@Override
	public boolean addOneUnit(Territory territory) {
		if (territory.addUnits(1, activePlayer.getTeam())) {
			this.notifyObservers(map);
			this.notifyObservers(territory);
			this.notifyObservers(this);// only useful for AI
			return true;
		} else
			return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see baseModel.CommandInterface#attackTerritory(baseModel.Player,
	 * java.lang.String, java.lang.String)
	 * 
	 * @author Stephen Brown at 6:17pm 11/29/11, Christopher Ray (Dice changes
	 * only) 11/30/11 7:26 PM
	 */
	@Override
	public boolean attackTerritory(Territory orig, Territory dest,
			int numOfAttackingDice) {
		// Check to see if the two territories are neighbors first, if not,
		// nothing happens, Attack Fails.
		if (orig.getNeighbors().contains(dest)) {

			int defenders = dest.getUnitsOnTerritory();
			if (defenders > 2)
				defenders = 2;

			// the attacking territory must be the player's, and there has to be
			// at least one unit left on the original territory.
			// Also max of 3 attacking Dice
			if ((orig.getOwningTeam() == activePlayer.getTeam())
					&& (numOfAttackingDice < orig.getUnitsOnTerritory())
					&& (numOfAttackingDice < 4) && (numOfAttackingDice >= 1)) {
				// records the beginning and ending number of attacking units
				int remainingArmy = numOfAttackingDice;

				rollAttackDice(numOfAttackingDice);
				// Sorts in descending order
				Collections.sort(this.getAttackDice(),
						Collections.reverseOrder());

				rollDefendDice();
				// Sorts in descending order
				Collections.sort(this.getDefendDice(),
						Collections.reverseOrder());

				// iterators for attacking and defending dice, resp.
				Iterator<Die> attackingDice = this.getAttackDice().iterator();
				Iterator<Die> defendingDice = this.getDefendDice().iterator();

				// Only goes for the lesser number of dice.
				while ((attackingDice.hasNext()) && (defendingDice.hasNext())) {
					Die attackingDie = attackingDice.next();
					Die defendingDie = defendingDice.next();

					// Die is now comparable...thus no need to compare their
					// rolls, you can just compare themselves directly
					if (attackingDie.compareTo(defendingDie) > 0)
						dest.removeUnits(1);
					else
						orig.removeUnits(1);
				}
				// capture:
				if (dest.getUnitsOnTerritory() == 0) {

					// find destination's owner player
					// Iterator<Player> playersItr = players.iterator();
					Player currentOwningPlayer = dest.getOwningPlayer();
					// new Player(null);
					// while (playersItr.hasNext()) {
					// current = playersItr.next();
					// if (current.getTeam() == dest.getOwningTeam())
					// break;
					// }
					currentOwningPlayer.getTerritoriesOwned().remove(dest);
					// check if current has no territories
					if (currentOwningPlayer.getNumberOfTerritories() == 0) {
						// give his or her cards to player activePlayer
						activePlayer.getCards().addAll(
								currentOwningPlayer.getCards());

						// remove current from players List
						players.remove(currentOwningPlayer);
						/*
						 * while(activePlayer.getCards().size()>4) { //request
						 * turn in cards from GUI }
						 */
						this.notifyObservers(players);

					}
					if (firstTerritory) {
						// add card
						drawCard();
						firstTerritory = false;
					}

					move(orig, dest, remainingArmy);
					if (activePlayer.getTerritoriesOwned().containsAll(
							map.getMap().get(dest.getParentContinent())
									.getChildren()))
						// award another territory card for capturing a
						// continent
						drawCard();
					updateMove(activePlayer, orig, dest);
				}

				/**
				 * @author Chris Ray 12/01/11 1:53 AM
				 */
				this.notifyObservers(activePlayer);
				this.notifyObservers(orig);
				this.notifyObservers(dest);
				this.notifyObservers(this);// only useful for AI

				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see baseModel.CommandInterface#move(baseModel.Player, java.lang.String,
	 * java.lang.String, int)
	 * 
	 * @author Chris Ray Created on 1:54:39 AM Nov 27, 2011
	 */

	@Override
	public boolean move(Territory orig, Territory dest, int numOfUnitsToMove) {
		try {
			// Check to see if the two territories are neighbors first, if not,
			// nothing happens, Move Fails.
			if (orig.getNeighbors().contains(dest))
				if ((orig.getOwningTeam() == activePlayer.getTeam())
						&& ((orig.getUnitsOnTerritory() > 1) && (numOfUnitsToMove < orig
								.getUnitsOnTerritory())))
					if ((dest.getUnitsOnTerritory() <= 0)
							|| (dest.getOwningTeam() == activePlayer.getTeam())) {
						// move is valid...proceed.
						// if numberOfUnitsToMove > orig.getUnitsOnTerritory()
						// move orig.getUnitsOnTerritory()-1 units
						if (numOfUnitsToMove > orig.getUnitsOnTerritory()) {
							orig.setUnitsOnTerritory(1);
							dest.setUnitsOnTerritory(orig.getUnitsOnTerritory() - 1);
						} else {
							orig.setUnitsOnTerritory(orig.getUnitsOnTerritory()
									- numOfUnitsToMove);
							dest.setUnitsOnTerritory(dest.getUnitsOnTerritory()
									+ numOfUnitsToMove);
						}
						this.notifyObservers(orig);
						this.notifyObservers(dest);
						this.notifyObservers(activePlayer);
						this.notifyObservers(this);// only useful for AI
						updateMove(activePlayer, orig, dest);
						return true;
					}
			return false;
		} catch (Exception e) {

			return false;
		}
	}

	/**
	 * 
	 * @author Chris Ray Created on 9:12:29 PM Nov 29, 2011
	 * 
	 */
	private void updateMove(Player p, Territory orig, Territory dest) {
		move.setP(p);
		move.setDest(dest);
		move.setOrig(orig);
		this.notifyObservers(move);
		this.notifyObservers(this);// only useful for AI

	}

	/**
	 * 
	 * @author Chris Ray Created on 6:42:19 PM Nov 30, 2011
	 * 
	 */
	private void unitMultiplierUp() {
		if (unitMultiplier < 10)
			unitMultiplier += 2;
		else
			unitMultiplier += 5;
		this.notifyObservers(unitMultiplier);
		this.notifyObservers(this);// only useful for AI
	}

	/**
	 * 
	 * @author Chris Ray Created on 6:42:24 PM Nov 30, 2011
	 * 
	 */
	private void rollAttackDice(int numOfDiceToRoll) {
		ArrayList<Die> returnDice = new ArrayList<Die>();
		if ((numOfDiceToRoll <= 3) && (numOfDiceToRoll >= 1))
			for (int i = 0; i < numOfDiceToRoll; i++) {
				attackDice.get(i).initiateRoll();
				returnDice.add(attackDice.get(i));
			}
		this.attackDice = returnDice;
		this.notifyObservers(this.attackDice);
	}

	/**
	 * 
	 * @author Chris Ray Created on 6:41:38 PM Nov 30, 2011 <br />
	 *         Assumes always will be rolling 2 dice
	 * 
	 */
	private void rollDefendDice() {
		for (Die d : defendDice)
			d.initiateRoll();
		this.notifyObservers(this.defendDice);
	}

	/**
	 * @return the map
	 * @author Chris Ray Created on 3:39:52 PM Nov 27, 2011
	 */
	@Override
	public Map getMap() {
		return map;
	}

	/**
	 * @param map
	 *            the map to set
	 * @author Chris Ray Created on 3:39:52 PM Nov 27, 2011
	 */
	@Override
	public boolean setMap(Map map) {
		try {
			this.map = map;
			this.notifyObservers(this.map);
			this.notifyObservers(this);// only useful for AI
			return true;
		} catch (Exception e) {
			System.err.print(e.getMessage());
			return false;
		}
	}

	/**
	 * @return the move
	 * @author Chris Ray Created on 9:07:24 PM Nov 29, 2011
	 */
	@Override
	public Move getMove() {
		return move;
	}

	/**
	 * @param move
	 *            the move to set
	 * @author Chris Ray Created on 9:07:24 PM Nov 29, 2011
	 */
	@Override
	public boolean setMove(Move move) {
		try {
			this.move = move;
			this.notifyObservers(move);
			return true;

		} catch (Exception e) {
			System.err.print(e.getMessage());
			return false;

		}
	}

	/**
	 * @return the players
	 * @author Chris Ray Created on 9:23:49 PM Nov 29, 2011
	 */
	@Override
	public LinkedList<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players
	 *            the players to set
	 * @author Chris Ray Created on 9:23:49 PM Nov 29, 2011
	 * @return
	 */
	@Override
	public boolean setPlayers(LinkedList<Player> players) {
		try {
			this.players = players;
			this.notifyObservers(players);
			this.notifyObservers(this);// only useful for AI
			return true;
		} catch (Exception e) {
			System.err.print(e.getMessage());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see baseModel.CommandInterface#getDefendDice()
	 * 
	 * @author Chris Ray Created on 5:36:45 PM Nov 30, 2011
	 */
	@Override
	public ArrayList<Die> getDefendDice() {
		return this.defendDice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see baseModel.CommandInterface#getAttackDice()
	 * 
	 * @author Chris Ray Created on 5:36:45 PM Nov 30, 2011
	 */
	@Override
	public ArrayList<Die> getAttackDice() {
		return this.attackDice;
	}

	/**
	 * @return the unitMultiplier
	 * @author Chris Ray Created on 7:24:23 PM Nov 30, 2011
	 */
	@Override
	public int getUnitMultiplier() {
		return unitMultiplier;
	}

	/**
	 * @param unitMultiplier
	 *            the unitMultiplier to set
	 * @author Chris Ray Created on 7:24:23 PM Nov 30, 2011
	 */
	@Override
	public boolean resetUnitMultiplier() {
		try {
			this.unitMultiplier = 4;
			this.notifyObservers(unitMultiplier);
			this.notifyObservers(this);// only useful for AI
			return true;
		} catch (Exception e) {
			System.err.print(e.getMessage());
			return false;
		}
	}

	/**
	 * @return the activePlayer
	 * @author Chris Ray Created on 8:01:31 AM Dec 2, 2011
	 */
	@Override
	public Player getActivePlayer() {
		return activePlayer;
	}

	/**
	 * @param activePlayer
	 *            the activePlayer to set
	 * @author Chris Ray Created on 8:01:31 AM Dec 2, 2011
	 */
	@Override
	public boolean setActivePlayer(Player activePlayer) {
		try {
			this.activePlayer = activePlayer;
			this.notifyObservers(activePlayer);
			this.notifyObservers(this);// only useful for AI
			return true;
		} catch (Exception e) {
			System.err.print(e.getMessage());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see baseModel.CommandInterface#allTerritoriesClaimed()
	 * 
	 * @author Chris Ray Created on 8:29:39 PM Dec 4, 2011
	 */
	@Override
	public boolean allTerritoriesClaimed() {
		for (Continent con : map.getMap().values())
			if (!con.isClaimed())
				return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see baseModel.CommandInterface#beginTurn()
	 * 
	 * @author Chris Ray Created on 8:42:05 PM Dec 4, 2011
	 */
	@Override
	public void awardBeginningUnits() {
		// based on number of territories owned
		activePlayer
				.setNewUnits((activePlayer.getTerritoriesOwned().size() / 3)
						+ activePlayer.getNewUnits());
		// based on continentsOwned
		int award = activePlayer.getNewUnits();
		for (Continent con : activePlayer.getContinentsOwned())
			award += con.getBonusUnitsIfOwned();
		activePlayer.setNewUnits(award);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * 
	 * @author Chris Ray Created on 1:35:19 PM Dec 4, 2011
	 */
	@Override
	public void update(Observable o, Object arg) {
		// for AI only.....sets the next player when AI finishes it's turn
		if ((o instanceof PlayerAI) && (arg instanceof Boolean))
			if ((activePlayer != players.getLast()) && (players.size() > 1))
				this.setActivePlayer(players.get((players
						.indexOf(this.activePlayer) + 1)));
			else
				this.setActivePlayer(players.getFirst());
	}

}
