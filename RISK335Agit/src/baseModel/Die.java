/**
 * @author Chris Ray
 * Created on 7:25:14 PM Nov 25, 2011
 *
 */
package baseModel;

import java.util.Random;

/**
 * @author Chris Ray Created on 7:25:14 PM Nov 25, 2011
 */
public class Die implements Comparable<Die> {// test
	private int roll;

	/**
	 * 
	 * @author Chris Ray Created on 8:12:25 PM Nov 26, 2011
	 * 
	 */
	public Die() {
		roll = 0;
	}

	/**
	 * @author Chris Ray Created on 8:12:27 PM Nov 26, 2011
	 * 
	 */
	public void initiateRoll() {
		Random randGen = new Random();
		roll = randGen.nextInt(6) + 1;
	}

	/**
	 * @return the roll
	 * @author Chris Ray Created on 7:28:04 PM Nov 25, 2011
	 */
	public final int getRoll() {
		return roll;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * 
	 * @author Chris Ray Created on 7:01:13 PM Nov 30, 2011
	 */
	@Override
	public int compareTo(Die d) {
		return (((Integer) this.getRoll()).compareTo(d.getRoll()));
	}

}
