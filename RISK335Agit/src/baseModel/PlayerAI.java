/**
 * @author Chris Ray
 * Created on 11:41:14 AM Dec 4, 2011
 *
 */
package baseModel;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Chris Ray Created on 11:41:14 AM Dec 4, 2011
 */
public class PlayerAI extends Player implements Observer {
	private Game game;
	private boolean turnOver;
	private boolean endAI;

	/**
	 * @param t
	 * @author Chris Ray Created on 11:41:27 AM Dec 4, 2011
	 * 
	 */
	public PlayerAI(Team t) {
		super(t);
		turnOver = true;
		endAI = false;
		game = new Game();
		// TODO Auto-generated constructor stub

		while (!endAI) {
			chooseCardsTurnIn();
			chooseUnitPlacement();
			chooseAttack();
			chooseMove();
			endTurn();
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
	 * @author Chris Ray Created on 11:52:56 AM Dec 4, 2011
	 * 
	 */
	private void chooseUnitPlacement() {
		// TODO Auto-generated method stub

	}

	/**
	 * @author Chris Ray Created on 11:52:58 AM Dec 4, 2011
	 * 
	 */
	private void chooseAttack() {
		// TODO Auto-generated method stub

	}

	/**
	 * @author Chris Ray Created on 11:53:07 AM Dec 4, 2011
	 * 
	 */
	private void chooseMove() {
		// TODO Auto-generated method stub

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
