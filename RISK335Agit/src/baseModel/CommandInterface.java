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
	 * @author Chris Ray Created on 8:21:29 PM Nov 26, 2011
	 * @param p
	 * @param orig
	 * @param dest
	 * @param numOfUnitsToMove
	 * @return True if successful, false otherwise
	 * 
	 *         Attempts to move a set number of units from one territory to
	 *         another.
	 * 
	 */
	public abstract boolean move(Player p, Territory orig, Territory dest,
			int numOfUnitsToMove);

	/**
	 * @author Chris Ray Created on 8:26:27 PM Nov 26, 2011
	 * @param p
	 * @return True if successful, false otherwise
	 * 
	 */
	public abstract boolean drawCard(Player p);

	/**
	 * @author Chris Ray Created on 8:26:32 PM Nov 26, 2011
	 * @param p
	 * @param cardsTurningIn
	 * @return
	 * 
	 */
	public abstract boolean turnInCards(Player p,
			ArrayList<TerritoryCard> cardsTurningIn);

	/**
	 * @author Chris Ray Created on 8:26:34 PM Nov 26, 2011
	 * @param p
	 * @param territory
	 * @param u
	 * @return
	 * 
	 */
	public abstract boolean placeOneUnitOnTerritory(Player p,
			Territory territory);

	/**
	 * @author Chris Ray Created on 8:26:37 PM Nov 26, 2011
	 * @param p
	 * @param destination
	 * @param origin
	 * @return
	 * 
	 */
	public abstract boolean attackTerritory(Player p, String destination,
			String origin);
}
