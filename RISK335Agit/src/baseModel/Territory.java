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
	private Team owningTeam;
	private int UnitsOnTerritory;
	private Player owningPlayer;
	/**
	 * This is for the AI, ignore this otherwise
	 * 
	 * @author Chris Ray Created on 2:11:03 PM Dec 04, 2011
	 * 
	 */
	private int placementPriority;

	/**
	 * @param name
	 * @param parentContinent
	 * @param neighbors
	 * @author Chris Ray Created on 2:16:02 AM Nov 27, 2011
	 * @param owningTeam
	 * 
	 */
	public Territory(String name, String parentContinent) {
		this.name = name;
		this.setNeighbors(null);
		this.parentContinent = parentContinent;
		this.setOwningTeam(null);
		this.setUnitsOnTerritory(0);
		this.setPlacementPriority(0);
		owningPlayer = new Player(null);
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
	 * @return the owningTeam
	 * @author Chris Ray Created on 2:18:13 AM Nov 27, 2011
	 */
	public Team getOwningTeam() {
		return owningTeam;
	}

	/**
	 * @param owningTeam
	 *            the owningTeam to set
	 * @author Chris Ray Created on 2:18:13 AM Nov 27, 2011
	 * @param owner
	 */
	public void setOwningTeam(Team owner) {
		this.owningTeam = owner;
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
			setOwningTeam(t);
			return true;
		} else if (getOwningTeam() == t) {
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

	/**
	 * @return the owningPlayer
	 * @author Chris Ray Created on 8:05:02 AM Dec 2, 2011
	 */
	public Player getOwningPlayer() {
		return owningPlayer;
	}

	/**
	 * @return the owningPlayer
	 * @author Chris Ray Created on 8:05:02 AM Dec 2, 2011
	 */
	public void setOwningPlayer(Player p) {
		this.owningPlayer = p;
	}

	/**
	 * This is for the AI, ignore otherwise
	 * 
	 * @return the placementPriority
	 * @author Chris Ray Created on 2:12:41 PM Dec 4, 2011
	 */
	public int getPlacementPriority() {
		return placementPriority;
	}

	/**
	 * This is for the AI, ignore otherwise
	 * 
	 * @param placementPriority
	 *            the placementPriority to set
	 * @author Chris Ray Created on 2:12:41 PM Dec 4, 2011
	 */
	public void setPlacementPriority(int placementPriority) {
		this.placementPriority = placementPriority;
	}

}
