/**
 * @author Chris Ray
 * Created on 9:01:50 PM Nov 29, 2011
 *
 */
package baseModel;

import java.io.Serializable;

/**
 * @author Chris Ray Created on 9:01:50 PM Nov 29, 2011 <br />
 * 
 *         Contains all the parameters if a move is made
 */
public class Move implements Serializable {
	/**
	 * @author Chris Ray Created on 9:04:34 PM Nov 29, 2011
	 * 
	 */
	private static final long serialVersionUID = -5421230992919092013L;
	private Player p;
	private Territory orig;
	private Territory dest;
	int numOfUnitsToMove;

	/**
	 * 
	 * @author Chris Ray Created on 9:03:27 PM Nov 29, 2011
	 * 
	 */
	public Move() {
		setP(null);
		setOrig(null);
		setDest(null);
	}

	/**
	 * @return the p
	 * @author Chris Ray Created on 9:05:27 PM Nov 29, 2011
	 */
	public Player getP() {
		return p;
	}

	/**
	 * @param p
	 *            the p to set
	 * @author Chris Ray Created on 9:05:27 PM Nov 29, 2011
	 */
	public void setP(Player p) {
		this.p = p;
	}

	/**
	 * @return the orig
	 * @author Chris Ray Created on 9:05:31 PM Nov 29, 2011
	 */
	public Territory getOrig() {
		return orig;
	}

	/**
	 * @param orig
	 *            the orig to set
	 * @author Chris Ray Created on 9:05:31 PM Nov 29, 2011
	 */
	public void setOrig(Territory orig) {
		this.orig = orig;
	}

	/**
	 * @return the dest
	 * @author Chris Ray Created on 9:05:36 PM Nov 29, 2011
	 */
	public Territory getDest() {
		return dest;
	}

	/**
	 * @param dest
	 *            the dest to set
	 * @author Chris Ray Created on 9:05:36 PM Nov 29, 2011
	 */
	public void setDest(Territory dest) {
		this.dest = dest;
	}

}
