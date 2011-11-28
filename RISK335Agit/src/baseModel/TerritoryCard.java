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
	}
}
