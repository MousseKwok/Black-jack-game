import java.util.ArrayList;
import java.util.Collections;

/**
 * Construct a deck containing 52 cards
 * @author Xijie Guo
 *
 */
public class Deck {
	//Use array list to store the deck
	private ArrayList<Card> deck;

	//Ranks of cards in the deck
	private String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

	/**
	 * Constructor of Deck
	 */
	public Deck() {
		buildDeck();
	}

	/**
	 * Construct a deck and shuffle it
	 */
	private void buildDeck() {
		deck = new ArrayList<Card>();
		//Add 52 cards to the deck array list
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++) {
				int point = getCardPoint(ranks[j]);
				Card card = new Card(ranks[j], point);
				deck.add(card);
			}
		}
		shuffleDeck();
	}

	/**
	 * Shuffle the deck
	 */
	private void shuffleDeck() {
		Collections.shuffle(deck);
		for(Card card : deck) {
			//Print the each card's rank and point for testing
			System.out.println(card.getRank() + " " + card.getPoint());
		}
	}
	
	/**
	 * get the corresponding point of the card
	 * @param rank the rank of the card
	 * @return the corresponding point of the card
	 */
	private int getCardPoint(String rank) {
		if(rank == ranks[0]) {
			return 11;
		}
		else if(rank == ranks[10] || rank == ranks[11] || rank == ranks[12]) {
			return 10;
		}
		else {
			int selectedRank = Integer.parseInt(rank);
			return selectedRank;
		}
	}
	
	/**
	 * Draw a card from the deck
	 */
	public Card drawCard() {
		Card removedCard = deck.remove(0);
		return removedCard;
	}
}
