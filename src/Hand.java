/**
 * Hand Interface 
 * @author Xijie Guo
 *
 */
public interface Hand {

	/**
	 * Initial deal(Both player and dealer should be dealt with two cards
	 * when they are in their turn
	 * @param deck the deck
	 */
	public void initialDeal(Deck deck);
	
	/**
	 * Add the card to the hand
	 * @param deck the deck
	 */
	public void hitCard(Deck deck);
	
	/**
	 * Check if the hand value is greater than 21
	 * @return true if the hand value is greater than 21
	 */
	public boolean isBusted();
	
	/**
	 * Calculate the total hand value
	 * @return the total hand value
	 */
	public int calculateHandVal();
	
	/**
	 * Get the calculated hand value
	 * @return the calculated hand value
	 */
	public int getHandVal();
	
	/**
	 * Get the string representation of cards
	 * @return the string representation of cards
	 */
	public String getCards();
	
	/**
	 * Clear the hand value of user
	 * @param handValue the total hand value
	 */
	public void clearHand(int handValue);
	
	/**
	 * Clear card list by removing all elements in the list
	 */
	public void clearCards();
	
	/**
	 * Check if the user can still hit(if their hand value already exceeds 21
	 * @return true if they can continue hitting
	 */
	public boolean canHit();
	
	/**
	 * Check if the user has a blackJack
	 * @return true if the user has a blackjack
	 */
	public boolean hasBlackJack();
}
