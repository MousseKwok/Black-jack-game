/**
 * Stores the information of the game. Keep tracking the progress
 * of the game
 * @author Xijie Guo
 *
 */
public class BlackJackGame {
	
	//boolean variable checking if it's player's turn 
	public boolean isPlayerTurn = false; 
	
	//Player's hand
	private Hand playerHand;
	
	//Dealer's hand
	private Hand dealerHand;
	
	//Player's number of wins 
	private int numWins = 0; 
	
	//Player's number of losses
	private int numLosses = 0;
	
	private Deck deck;
	
	/**
	 * Constructor of BlackJackGame
	 */
	public BlackJackGame() {
		playerHand = new PlayerHand();
		dealerHand = new DealerHand();
		deck = new Deck();
		//Player is always the first to play the game
		startPlayerTurn(deck);
	}
	
	/**
	 * Start player's turn
	 * @param deck the deck of cards
	 */
	private void startPlayerTurn(Deck deck) {
		isPlayerTurn = true; 
		playerHand.initialDeal(deck);
	}
	
	/**
	 * Start dealer's turn
	 * @param deck the deck of cards
	 */
	public void startDealerTurn(Deck deck) {
		dealerHand.initialDeal(deck);
	}
	
	/**
	 * Get the player's hand
	 * @return the player's hand
	 */
	public PlayerHand getPlayerHand() {
		return (PlayerHand) playerHand;
	}
	
	/**
	 * Get the dealer's hand
	 * @return the dealer's hand
	 */
	public DealerHand getDealerHand() {
		return (DealerHand) dealerHand; 
	}
	
	/**
	 * Check if it's in the player's turn
	 * @return true if it's in the player's turn
	 */
	public boolean isPlayerTurn() {
		if(isPlayerTurn) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check if the player's hand value and the dealer's hand value are the same
	 * @return true if they are the same
	 */
	private boolean isPush() {
		if(playerHand.getHandVal() == dealerHand.getHandVal()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check if the player wins
	 * @return true if the player wins
	 */
	private boolean hasPlayerWon() {
		if(playerHand.hasBlackJack() && !dealerHand.hasBlackJack()) {
			return true;
		}
		else if(!playerHand.isBusted() && 
				playerHand.getHandVal() > dealerHand.getHandVal()) {
			return true;
		}
		else if(!playerHand.isBusted() &&
				dealerHand.isBusted()) {
			return true;
		}
		return false;
	}

	/**
	 * Get the number of player's wins
	 * @return the number of times that the player wins
	 */
	public int getPlayerNumWins() {
		if(hasPlayerWon()) {
			numWins++;
		}
		return numWins;
	}
	
	/**
	 * Get the number of player's losses
	 * @return the number of times that the play loses
	 */
	public int getPlayerNumLosses() {
		if(!hasPlayerWon()) {
			//Only count as losses if the player's hand value and the dealer's 
			// hand value are not the same
			if(!isPush()) {
				numLosses++;
			}
			
		}
		return numLosses;
	}
	
	/**
	 * Start a new round
	 */
	public void startNewRound() {
		//Clear player and dealer's hand value
		playerHand.clearHand(0);
		dealerHand.clearHand(0);
		//Clear player and dealer's cards
		playerHand.clearCards();
		dealerHand.clearCards();
		
		//Create a new deck 
		Deck newDeck = new Deck();
		this.deck = newDeck;
		
		//Start player's turn with the new deck
		startPlayerTurn(deck);
	}
	
	/**
	 * Get the deck
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}

}
