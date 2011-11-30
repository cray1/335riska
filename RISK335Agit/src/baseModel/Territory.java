/**
 * @author Chris Ray
 * Created on 2:10:44 AM Nov 27, 2011
 *
 */
package baseModel;

import java.util.ArrayList;

/**
 * @author Chris Ray Created on 2:10:44 AM Nov 27, 2011
 */
public class Territory {

	private ArrayList<Territory> neighbors;
	private final String name;
	private final String parentContinent;
	private Team owner;
	private int UnitsOnTerritory;

	/**
	 * @param name
	 * @param parentContinent
	 * @param neighbors
	 * @author Chris Ray Created on 2:16:02 AM Nov 27, 2011
	 * @param owner
	 * 
	 */
	public Territory(String name, String parentContinent) {
		this.name = name;
		this.setNeighbors(null);
		this.parentContinent = parentContinent;
		this.setOwner(null);
		this.setUnitsOnTerritory(0);
	}

	/**
	 * @return the name
	 * @author Chris Ray Created on 2:16:23 AM Nov 27, 2011
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * @return the parentContinent
	 * @author Chris Ray Created on 2:16:30 AM Nov 27, 2011
	 */
	public String getParentContinent() {
		return parentContinent;
	}

	/**
	 * @return the owner
	 * @author Chris Ray Created on 2:18:13 AM Nov 27, 2011
	 */
	public Team getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 * @author Chris Ray Created on 2:18:13 AM Nov 27, 2011
	 */
	public void setOwner(Team owner) {
		this.owner = owner;
	}

	/**
	 * @return the unitsOnTerritory
	 * @author Chris Ray Created on 2:21:12 AM Nov 27, 2011
	 */
	public int getUnitsOnTerritory() {
		return UnitsOnTerritory;
	}

	/**
	 * @param unitsOnTerritory
	 *            the unitsOnTerritory to set
	 * @author Chris Ray Created on 2:21:12 AM Nov 27, 2011
	 */
	public void setUnitsOnTerritory(int unitsOnTerritory) {
		UnitsOnTerritory = unitsOnTerritory;
	}

	/**
	 * 
	 * @param territory
	 * @param u
	 * @param player
	 * @return
	 * 
	 * @author Stephen 3:55pm 11/29/11
	 */
	public boolean addUnits(int u, Team t) {
		if (getUnitsOnTerritory() == 0) {
			setUnitsOnTerritory(u);
			setOwner(t);
			return true;
		} else if (getOwner() == t) {
			setUnitsOnTerritory(u + getUnitsOnTerritory());
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param territory
	 * @param u
	 * 
	 * @author Stephen Brown at 3:52 11/29/11
	 * @param unit
	 * @return
	 */
	public boolean removeUnits(int unit) {
		try {

			int remove = getUnitsOnTerritory() - unit;
			if (remove > 0)
				setUnitsOnTerritory(getUnitsOnTerritory() - unit);
			else
				setUnitsOnTerritory(0);
			// setOwner(null);
			return true;
		} catch (Exception e) {
			System.err.print("Remove Failed");
			return false;
		}
	}

	/**
	 * @return the neighbors
	 * @author Chris Ray Created on 2:47:34 AM Nov 27, 2011
	 */
	public final ArrayList<Territory> getNeighbors() {
		return neighbors;
	}

	/**
	 * @param neighbors
	 *            the neighbors to set
	 * @author Chris Ray Created on 2:47:34 AM Nov 27, 2011
	 */
	public void setNeighbors(ArrayList<Territory> neighbors) {
		this.neighbors = neighbors;
	}

}
