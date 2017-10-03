/**
 * DealerHand is a subclas of UserHand since dealer can be seen as a kind of user
 * @author Xijie Guo
 *
 */
public class DealerHand extends UserHand{
	
	/**
	 * Check if the dealer should hit. The dealer should always hit if 
	 * its hand value is less than 17
	 * @return true if the dealer should hit
	 */
	public boolean shouldHit() {
		if(getHandVal() < 17) {
			return true;
		}
		return false; 
	}
	
}
