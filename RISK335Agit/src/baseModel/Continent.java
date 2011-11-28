/**
 * @author Chris Ray
 * Created on 2:13:57 AM Nov 27, 2011
 *
 */
package baseModel;

import java.util.ArrayList;

/**
 * @author Chris Ray Created on 2:13:57 AM Nov 27, 2011
 */
public class Continent {
	private final String name;
	private final ArrayList<Territory> children;
	private final int bonusUnitsIfOwned;
	private Player owner;

	/**
	 * @param name
	 * @param children
	 * @author Chris Ray Created on 2:24:05 AM Nov 27, 2011
	 * @param bonusUnitsIfOwned
	 * 
	 */
	public Continent(String name, ArrayList<Territory> children,
			int bonusUnitsIfOwned) {
		this.name = name;
		this.children = children;
		this.bonusUnitsIfOwned = bonusUnitsIfOwned;
		this.setOwner(null);

	}

	/**
	 * @return the children
	 * @author Chris Ray Created on 2:24:14 AM Nov 27, 2011
	 */
	public ArrayList<Territory> getChildren() {
		return children;
	}

	/**
	 * @return the name
	 * @author Chris Ray Created on 2:24:17 AM Nov 27, 2011
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the bonusUnitsIfOwned
	 * @author Chris Ray Created on 2:25:53 AM Nov 27, 2011
	 */
	public int getBonusUnitsIfOwned() {
		return bonusUnitsIfOwned;
	}

	/**
	 * @return the owner
	 * @author Chris Ray Created on 2:27:19 AM Nov 27, 2011
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 * @author Chris Ray Created on 2:27:19 AM Nov 27, 2011
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
}
