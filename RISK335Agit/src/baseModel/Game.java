/**
 * @author Chris Ray
 * Created on 8:15:11 PM Nov 26, 2011
 *
 */
package baseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Chris Ray Created on 8:15:11 PM Nov 26, 2011
 */
public class Game extends CommandInterface {
	private CardDeck deck;
	private int unitMultiplier;
	private Map map;
	private Boolean firstTerritory;
	private Move move;
	private LinkedList<Player> players;

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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see baseModel.CommandInterface#drawCard(baseModel.Player)
	 * 
	 * @author Chris Ray Created on 8:27:35 PM Nov 26, 2011
	 */
	@Override
	public boolean drawCard(Player p) {
		try {
			p.getCards().add(deck.drawCard());
			this.notifyObservers(p);
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
	public boolean turnInCards(Player p, ArrayList<TerritoryCard> cardsTurningIn) {
		if ((cardsTurningIn.size() == 3) && isValidTurnin(cardsTurningIn)) {
			p.getCards().removeAll(cardsTurningIn);
			this.notifyObservers(p);
			return true;
		} else
			return true;
	}

	private boolean isValidTurnin(ArrayList<TerritoryCard> cards) {
		// 2 and a wildcard or 1 and 2 wildcards
		if (cards.contains(CardType.WILDCARD) && (cards.size() == 3))
			return true;
		else if ((cards.get(0).getCardType() == cards.get(1).getCardType())
				&& (cards.get(1).getCardType() == cards.get(2).getCardType()))
			return true;
		// 1 of each
		else if (cards.contains(CardType.CANNON)
				&& cards.contains(CardType.HORSE)
				&& cards.contains(CardType.SOLDIER))
			return true;

		else
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
	public boolean placeOneUnitOnTerritory(Player p, Territory territory) {
		if (territory.getUnitsOnTerritory() == 0) {
			int i = map.getTerritories().indexOf(territory);
			map.getTerritories()
					.get(i)
					.setUnitsOnTerritory(
							map.getTerritories().get(i).getUnitsOnTerritory() + 1);
			map.getTerritories().get(i).setOwner(p.getTeam());
			p.setNumberOfTerritories(p.getNumberOfTerritories() + 1);
			this.notifyObservers(p);
			this.notifyObservers(map.getTerritories().get(i));
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see baseModel.CommandInterface#attackTerritory(baseModel.Player,
	 * java.lang.String, java.lang.String)
	 * 
	 * @author Chris Ray Created on 8:27:35 PM Nov 26, 2011
	 */
	@Override
	/**
	 * the attack method 
	 * 
	 * @param p the player attacking
	 * @param origin the territory the player is attacking from
	 * @param destination the territory the player is attacking
	 * @param attackingPieces the amount of units the player is attacking with
	 * @author Stephen Brown at 6:17pm 11/29/11
	 */
	public boolean attackTerritory(Player p, Territory origin,
			Territory destination, int attackingDice) {
		// Check to see if the two territories are neighbors first, if not,
		// nothing happens, Attack Fails.
		if (origin.getNeighbors().contains(destination)) {
			List<Integer> diceA = new ArrayList<Integer>();// attacker's dice
			List<Integer> diceD = new ArrayList<Integer>();// defender's dice
			List<Die> attDice = new ArrayList<Die>();
			Die a1 = new Die();
			Die a2 = new Die();
			Die a3 = new Die();
			attDice.add(a1);
			attDice.add(a2);
			attDice.add(a3);
			List<Die> defDice = new ArrayList<Die>();
			Die d1 = new Die();
			Die d2 = new Die();
			defDice.add(d1);
			defDice.add(d2);

			Iterator<Die> aD = attDice.iterator();
			Iterator<Die> dD = defDice.iterator();

			int defenders = destination.getUnitsOnTerritory();
			if (defenders > 2)
				defenders = 2;
			Die temp;
			if ((origin.getOwner() == p.getTeam())
					&& (attackingDice < origin.getUnitsOnTerritory())
					&& (attackingDice < 4))
			// the attacking territory must be the player's, and there has to be
			// at
			// least
			// one unit left on the original territory. Also max of 3 attacking
			// Dice
			{
				int remainingArmy = attackingDice;// records the beginning and
													// ending number of
													// attacking
													// units
				for (int n = 0; n < attackingDice; n++)// makes a die roll for
														// each
														// attacking unit
				{
					temp = aD.next();
					temp.initiateRoll();
					diceA.add(temp.getRoll());
				}
				Collections.sort(diceA, Collections.reverseOrder());// sorts in
																	// descending
																	// order
				for (int n = 0; n < defenders; n++)// makes a die roll for each
													// defending unit
				{
					temp = dD.next();
					temp.initiateRoll();
					diceD.add(temp.getRoll());
				}
				Collections.sort(diceD, Collections.reverseOrder());

				Iterator<Integer> att = diceA.iterator();// iterators for
															// attacking
															// and defending
															// dice,
															// resp.
				Iterator<Integer> def = diceD.iterator();

				while ((att.hasNext()) && (def.hasNext()))// only goes for the
															// lesser number of
															// dice
				{
					Integer attackNum = att.next();
					Integer defNum = def.next();

					if (attackNum > defNum)
						destination.removeUnits(1);
					else
						origin.removeUnits(1);
				}
				// capture:
				if (destination.getUnitsOnTerritory() == 0) {

					// find destination's owner player
					Iterator<Player> playersItr = players.iterator();
					Player current = new Player(null);
					while (playersItr.hasNext()) {
						current = playersItr.next();
						if (current.getTeam() == destination.getOwner())
							break;
					}
					current.setNumberOfTerritories(current
							.getNumberOfTerritories() - 1);
					// check if current has no territories
					if (current.getNumberOfTerritories() == 0) {
						// give his or her cards to player p
						p.getCards().addAll(current.getCards());

						// remove current from players List
						players.remove(current);
						/*
						 * while(p.getCards().size()>4) { //request turn in
						 * cards from GUI }
						 */
						this.notifyObservers(players);

					}
					if (firstTerritory) {
						// add card
						drawCard(p);
						firstTerritory = false;
					}
					move(p, origin, destination, remainingArmy);
					updateMove(p, origin, destination);
				}

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
	public boolean move(Player p, Territory orig, Territory dest,
			int numOfUnitsToMove) {
		try {
			// Check to see if the two territories are neighbors first, if not,
			// nothing happens, Move Fails.
			if (orig.getNeighbors().contains(dest))
				if ((orig.getOwner() == p.getTeam())
						&& ((orig.getUnitsOnTerritory() > 1) && (numOfUnitsToMove < orig
								.getUnitsOnTerritory())))
					if ((dest.getUnitsOnTerritory() <= 0)
							|| (dest.getOwner() == p.getTeam())) {

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
						updateMove(p, orig, dest);
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

	}

	private void unitMultiplierUp() {
		if (unitMultiplier < 10)
			unitMultiplier += 2;
		else
			unitMultiplier += 5;
	}

	/**
	 * @return the map
	 * @author Chris Ray Created on 3:39:52 PM Nov 27, 2011
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @param map
	 *            the map to set
	 * @author Chris Ray Created on 3:39:52 PM Nov 27, 2011
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * @return the move
	 * @author Chris Ray Created on 9:07:24 PM Nov 29, 2011
	 */
	public Move getMove() {
		return move;
	}

	/**
	 * @param move
	 *            the move to set
	 * @author Chris Ray Created on 9:07:24 PM Nov 29, 2011
	 */
	public void setMove(Move move) {
		this.move = move;
	}

	/**
	 * @return the players
	 * @author Chris Ray Created on 9:23:49 PM Nov 29, 2011
	 */
	public LinkedList<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players
	 *            the players to set
	 * @author Chris Ray Created on 9:23:49 PM Nov 29, 2011
	 */
	public void setPlayers(LinkedList<Player> players) {
		this.players = players;
	}
}
