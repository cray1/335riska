/**
 * @author Chris Ray
 * Created on 11:41:14 AM Dec 4, 2011
 *
 */
package baseModel;

/**
 * @author Chris Ray Created on 11:41:14 AM Dec 4, 2011
 */
public class PlayerAI extends Player {

	/**
	 * @param t
	 * @author Chris Ray Created on 11:41:27 AM Dec 4, 2011
	 * 
	 */
	public PlayerAI(Team t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Notifies the AI that it's turn has started.
	 * 
	 * @author Chris Ray Created on 11:50:28 AM Dec 4, 2011
	 * 
	 */
	public void startTurn() {
		chooseCardsTurnIn();
		chooseUnitPlacement();
		chooseAttack();
		chooseMove();
		endTurn();

	}

	/**
	 * @author Chris Ray Created on 11:53:10 AM Dec 4, 2011
	 * 
	 */
	private void endTurn() {
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
	 * @author Chris Ray Created on 11:52:58 AM Dec 4, 2011
	 * 
	 */
	private void chooseAttack() {
		// TODO Auto-generated method stub

	}

	/**
	 * @author Chris Ray Created on 11:52:56 AM Dec 4, 2011
	 * 
	 */
	private void chooseUnitPlacement() {
		// TODO Auto-generated method stub

	}

	/**
	 * @author Chris Ray Created on 11:52:53 AM Dec 4, 2011
	 * 
	 */
	private void chooseCardsTurnIn() {
		// TODO Auto-generated method stub

	}
}
