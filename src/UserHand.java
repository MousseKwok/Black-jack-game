import java.util.ArrayList;

/**
 * General user hand. Player hand and dealer hand are both counted as 
 * user hands. Having this class is to reuse code(avoid code repetition)
 * @author Xijie Guo
 *
 */
public abstract class UserHand implements Hand {

	//the card array list to store cards in a user's hand
	protected ArrayList<Card> cards = new ArrayList<Card>();
	
	//hand value of a user
	protected int handValue;
	
	/**
	 * Initial deal(Both player and dealer should be dealt with two cards
	 * when they are in their turn
	 */
	@Override
	public void initialDeal(Deck deck) {
		cards.add(deck.drawCard());
		cards.add(deck.drawCard());
	}

	/**
	 * Add cards to the hand
	 */
	@Override
	public void hitCard(Deck deck) {
		Card hitCard = deck.drawCard();
		cards.add(hitCard);
		calculateHandVal();
	}

	/**
	 * Check if the hand value is greater than 21
	 */
	@Override
	public boolean isBusted() {
		return getHandVal() > 21;
	}

	/**
	 * Calculate the hand value
	 */
	@Override
	public int calculateHandVal() {
		handValue = 0;
		//Create an array to store cards and convert the array list to array
		Card[] cardList = new Card[cards.size()];
		cardList = cards.toArray(cardList);
		for(int i = 0; i < cardList.length; i++) {
			//Update the hand value
			handValue += cardList[i].getPoint();
			
			//Special case: when the rank of the card is "Ace"
			if(cardList[i].getRank().equals("Ace")) {
				if(handValue > 21) {
					handValue -= 10;
				}
			}
			
		}
		return handValue;
	}

	/**
	 * Get the hand value
	 */
	@Override
	public int getHandVal() {
		return calculateHandVal();
	}

	/**
	 * Get the string representation of cards
	 */
	@Override
	public String getCards() {
		String cardsToString = "";
		Card[] cardList = new Card[cards.size()];
		cardList = cards.toArray(cardList);
		for(int i = 0; i < cardList.length; i++) {
			cardsToString += cardList[i].getRank() + " ";
		}
		return cardsToString;
	}

	/**
	 * Clear user's hand value
	 */
	@Override
	public void clearHand(int handValue) {
		this.handValue = handValue;
	}

	/**
	 * Clear the card list
	 */
	@Override
	public void clearCards() {
		cards.clear();
	}

	/**
	 * Check if the user can still conduct hitting
	 * @return true if the user can hit
	 */
	@Override
	public boolean canHit() {
		if(getHandVal() < 21) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check if the user has a black jack
	 */
	public boolean hasBlackJack() {
		if(calculateHandVal() == 21 && cards.size() == 2) {
			return true;
		}
		return false;
	}
}
