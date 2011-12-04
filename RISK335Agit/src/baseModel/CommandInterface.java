/**
 * @author Chris Ray
 * Created on 8:20:02 PM Nov 26, 2011
 *
 */
package baseModel;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Chris Ray Created on 8:20:02 PM Nov 26, 2011
 */
public abstract class CommandInterface extends Observable {

	/**
	 * Attempts to move a set number of units from one territory to another.
	 * 
	 * @author Chris Ray Created on 8:21:29 PM Nov 26, 2011
	 * @param p
	 * @param orig
	 * @param dest
	 * @param numOfUnitsToMove
	 * @return True if successful, false otherwise
	 * 
	 * 
	 * 
	 */
	public abstract boolean move(Territory orig, Territory dest,
			int numOfUnitsToMove);

	/**
	 * @author Chris Ray Created on 5:31:06 PM Nov 30, 2011
	 * @return ArrayList<Die> representing the two defending die
	 * 
	 */
	public abstract ArrayList<Die> getDefendDice();

	/**
	 * @author Chris Ray Created on 5:31:10 PM Nov 30, 2011
	 * @return ArrayList<Die> representing the three attacking die
	 * 
	 */
	public abstract ArrayList<Die> getAttackDice();

	/**
	 * @author Chris Ray Created on 8:26:27 PM Nov 26, 2011
	 * @param p
	 * @return True if successful, false otherwise
	 * 
	 */
	public abstract boolean drawCard();

	/**
	 * @author Chris Ray Created on 8:26:32 PM Nov 26, 2011
	 * @param p
	 * @param cardsTurningIn
	 * @return boolean representing rather or not the command went through
	 * 
	 */
	public abstract boolean turnInCards(TerritoryCard card1, TerritoryCard card2, TerritoryCard card3);

	/**
	 * This is only for when you are placing the first unit on a territory
	 * (during the special placement phase at the beginning of the game where
	 * all the players are placing their initial units) <br />
	 * 
	 * @author Chris Ray Created on 8:26:34 PM Nov 26, 2011
	 * @param p
	 * @param territory
	 * @param u
	 * @return boolean representing rather or not the command went through
	 * 
	 */
	public abstract boolean claimTerritory(Territory territory);

	/**
	 * @author Stephen Brown at 6:17pm 11/29/11, Christopher Ray (Dice changes
	 *         only) 11/30/11 7:26 PM
	 * @param p
	 * @param destination
	 * @param origin
	 * @param numOfAttackingDice
	 *            represents the number of Dice attacking with. min =1, max =3
	 * @return boolean representing rather or not the command went through
	 * 
	 */
	public abstract boolean attackTerritory(Territory origin,
			Territory destination, int numOfAttackingDice);

}
