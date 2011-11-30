/**
 * @author Chris Ray
 * Created on 8:15:11 PM Nov 26, 2011
 *
 */
package baseModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Chris Ray Created on 8:15:11 PM Nov 26, 2011
 */
public class Game extends CommandInterface {
	private CardDeck deck;
	private int unitMultiplier;
	private Map map;

	/**
	 * 
	 * @author Chris Ray Created on 8:16:57 PM Nov 26, 2011
	 * 
	 */
	public Game() {
		deck = new CardDeck();
		unitMultiplier = 4;
		setMap(new Map());
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
			return true;
		} else
			return true;
	}

	private boolean isValidTurnin(ArrayList<TerritoryCard> cards) {
		// 2 and a wildcard or 1 and 2 wildcards
		if (cards.contains(CardType.WILDCARD) && (cards.size() == 3))
			return true;
		// 3 of a kind
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
	public boolean attackTerritory(Player p, String destination, String origin) {
		// TODO Auto-generated method stub
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

			if ((orig.getOwner() == p.getTeam())
					&& ((orig.getUnitsOnTerritory() > 1) && (numOfUnitsToMove < orig
							.getUnitsOnTerritory())))
				if ((dest.getUnitsOnTerritory() <= 0)
						|| (dest.getOwner() == p.getTeam())) {
					orig.setUnitsOnTerritory(orig.getUnitsOnTerritory()
							- numOfUnitsToMove);
					dest.setUnitsOnTerritory(dest.getUnitsOnTerritory()
							+ numOfUnitsToMove);
					this.notifyObservers(orig);
					this.notifyObservers(dest);
					return true;
				} else
					return false;
			return true;
		} catch (Exception e) {

			return false;
		}
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
}
