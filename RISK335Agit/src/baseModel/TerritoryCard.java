/**
 * @author Chris Ray
 * Created on 6:08:01 PM Nov 25, 2011
 *
 */
package baseModel;

/**
 * @author Chris Ray Created on 6:08:01 PM Nov 25, 2011
 */
public class TerritoryCard {

	private CardType cardType;
	private Territory cardTerritory;

	/**
	 * @return the cardType
	 * @author Chris Ray Created on 6:15:24 PM Nov 25, 2011
	 */
	public CardType getCardType() {
		return cardType;
	}

	/**
	 * @param type
	 * @author Chris Ray <br />
	 *         Created on 6:17:11 PM Nov 25, 2011 <br />
	 *         Constructor: Creates a new TerritoryCard
	 * 
	 */
	public TerritoryCard(CardType type) {
		cardType = type;
		setCardTerritory(null);
	}

	/**
	 * @return the cardTerritory
	 * @author Chris Ray Created on 4:36:36 PM Nov 29, 2011
	 */
	public Territory getCardTerritory() {
		return cardTerritory;
	}

	/**
	 * @param cardTerritory
	 *            the cardTerritory to set
	 * @author Chris Ray Created on 4:36:36 PM Nov 29, 2011
	 */
	public void setCardTerritory(Territory cardTerritory) {
		this.cardTerritory = cardTerritory;
	}
}
