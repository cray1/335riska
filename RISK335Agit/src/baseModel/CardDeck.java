/**
 * @author Chris Ray
 * Created on 6:37:08 PM Nov 25, 2011
 *
 */
package baseModel;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

/**
 * @author Chris Ray Created on 6:37:08 PM Nov 25, 2011
 */
public class CardDeck {// //
	private Stack<TerritoryCard> deck;

	/***
	 * Creates a new deck with
	 * 
	 * @author Chris Ray Created on 6:41:15 PM Nov 25, 2011
	 * 
	 */
	public CardDeck() {
		deck = new Stack<TerritoryCard>();
		// push Soldier cards onto stack 11each
		TerritoryCard soldier = new TerritoryCard(CardType.SOLDIER);
		for (int i = 0; i < 14; i++)
			deck.push(soldier);
		// push horse cards onto stack 11each
		TerritoryCard horse = new TerritoryCard(CardType.HORSE);
		for (int i = 0; i < 14; i++)
			deck.push(horse);
		// push cannon cards onto stack 11each
		TerritoryCard cannon = new TerritoryCard(CardType.CANNON);
		for (int i = 0; i < 14; i++)
			deck.push(cannon);
		// push wild cards onto stack 9each
		TerritoryCard wild = new TerritoryCard(CardType.WILDCARD);
		for (int i = 0; i < 2; i++)
			deck.push(wild);
		// shuffle deck
		shuffle();
		assignTerritories();

	}

	/**
	 * @author Chris Ray Created on 4:27:17 PM Nov 29, 2011
	 * 
	 */
	private void assignTerritories() {
		Iterator<Territory> territoriesList = (new Map()).getTerritories()
				.iterator();
		for (TerritoryCard card : deck)
			if (territoriesList.hasNext())
				card.setCardTerritory(territoriesList.next());
	}

	/**
	 * @author Chris Ray Created on 6:39:30 PM Nov 25, 2011
	 * @return TerritoryCard from the top of the stack
	 * 
	 */
	public TerritoryCard drawCard() {
		return deck.pop();

	}

	/**
	 * @author Chris Ray Created on 6:54:28 PM Nov 25, 2011
	 * 
	 */
	public void shuffle() {
		Collections.shuffle(deck);
	}

	/**
	 * @author Chris Ray Created on 6:54:31 PM Nov 25, 2011
	 * @return int representing the size of the deck
	 * 
	 */
	public int getSize() {
		return deck.size();
	}

}
