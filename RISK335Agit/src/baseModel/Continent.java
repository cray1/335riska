/**
 * @author Chris Ray
 * Created on 2:13:57 AM Nov 27, 2011
 *
 */
package baseModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Chris Ray Created on 2:13:57 AM Nov 27, 2011
 */
public class Continent {
	private final String name;
	private final ArrayList<Territory> children;
	private final int bonusUnitsIfOwned;
	private Team owner;

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
	 * @author Chris Ray Created on 2:30:12 PM Nov 29, 2011
	 * @return Hashmap representing the children of a continent
	 * 
	 */
	public HashMap<String, Territory> getChildrenAsHashMap() {
		HashMap<String, Territory> childTerritories = new HashMap<String, Territory>();
		for (Territory ter : this.children)
			childTerritories.put(ter.toString(), ter);

		return childTerritories;
	}

	/**
	 * @return the name
	 * @author Chris Ray Created on 2:24:17 AM Nov 27, 2011
	 */
	public String getName() { //
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
	public Team getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 * @author Chris Ray Created on 2:27:19 AM Nov 27, 2011
	 */
	public void setOwner(Team owner) {
		this.owner = owner;
	}

	/**
	 * @return true if there is at least one territory on this continent with a
	 *         non null team
	 * @author Chris Ray Created on 5:21:35 PM Dec 4, 2011
	 */
	public boolean isClaimed() {
		for (Territory ter : children)
			if (ter.getOwningTeam() != null)
				return true;
		return false;
	}

	/**
	 * @author Chris Ray Created on 5:36:45 PM Dec 4, 2011
	 * @param team
	 * @return true if there is an enemy in this continent
	 * 
	 */
	public boolean hasEnemy(Team team) {
		for (Territory ter : children)
			if (ter.getOwningTeam() != team)
				return true;
		return false;
	}
}
